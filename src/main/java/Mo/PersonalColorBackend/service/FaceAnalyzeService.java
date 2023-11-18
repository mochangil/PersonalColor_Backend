package Mo.PersonalColorBackend.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FaceAnalyzeService {

//    private final AmazonS3 amazonS3;
//    @Value("${aws.s3.bucket}")
//    private String bucket;


    @Transactional
    public String getImageUrl(String requestUrl) throws IOException{
        int index = requestUrl.lastIndexOf("/");
        String filename = null;
        if(index > 0) {
            filename = requestUrl.substring(index+1);
        }
        String output_image_path = "/home/ubuntu/app/src/main/java/Mo/PersonalColorBackend/ML/personal/"+filename;
        URL url = null;
        InputStream in = null;
        OutputStream out = null;
        try{
            url = new URL(requestUrl);
            in = url.openStream();
            out = new FileOutputStream(output_image_path);
            while(true){
                int data = in.read();
                if(data == -1){
                    break;
                }
                out.write(data);
            }
            in.close();
            out.close();
        } catch (IOException e){
            e.printStackTrace();
        }finally {
            if(in != null){in.close();}
            if(out != null){out.close();}
            return output_image_path;
        }

    }

    @Transactional
    public String runAnalyzePython(String image_path) throws IOException, InterruptedException {
        try{
            List<String> command = new ArrayList<>();

//            String image_path = "/home/ubuntu/app/src/main/java/Mo/PersonalColorBackend/ML/personal/spring1.png";
            String Python_path = "/home/ubuntu/app/src/main/java/Mo/PersonalColorBackend/ML/personal/src/main.py";
            command.add("python3"); // 또는 "python3" 등의 적절한 명령어 사용
            command.add(Python_path);
            command.add("--image");
            command.add(image_path);

            ProcessBuilder builder = new ProcessBuilder(command);
            Process process = builder.start();

            // Python 스크립트의 출력을 읽음
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }

            System.out.println("python 실행 check");
            // 프로세스가 끝날 때까지 기다림
            process.waitFor();
            System.out.println("python check 1");
            in.close();
            //0에서7
            System.out.println(result.toString());
            return result.toString();

        }catch (IOException | InterruptedException e){
            return "오류 발생: " + e.getMessage();
        }
    }
    @Transactional
    public void deleteImage(String image_path){
        File file = new File(image_path);

        if(file.exists()){
            if(file.delete()){
                System.out.println("파일삭제 성공");
            }else{
                System.out.println("파일삭제 실패");
            }
        }else{
            System.out.println("파일이 존재하지 않습니다.");
        }
    }
}
