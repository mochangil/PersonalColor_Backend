package Mo.PersonalColorBackend.controller;


import Mo.PersonalColorBackend.service.FaceAnalyzeService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.bridge.Message;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpHeaders.SET_COOKIE;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/face")
public class FaceAnalyzeController {

    private final FaceAnalyzeService faceAnalyzeService;

    //@PostMapping
    @GetMapping("/token")
    public ResponseEntity personalColorAnalyze( //@RequestBody String img,
                                                HttpServletResponse response,
                                                @CookieValue(value="personalColorId", required = false)Cookie cookie){
        try {
            //analyze face info, find personalColor.
            String id = "1";
            id = URLEncoder.encode(id, "utf-8");
            Cookie newCookie = new Cookie("personalColor",id);
            newCookie.setPath("/");
            newCookie.setMaxAge(30);
            response.addCookie(newCookie);
            return new ResponseEntity(HttpStatus.OK);
        }catch(UnsupportedEncodingException e){
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }


    }

    @GetMapping("/analyze2")
    @Async
    public String executePythonScript2() throws IOException, InterruptedException{
        String image_path = "/home/ubuntu/app/src/main/java/Mo/PersonalColorBackend/ML/personal/spring1.png";
        String python_path = "/home/ubuntu/app/src/main/java/Mo/PersonalColorBackend/ML/personal/src/main.py";
        ProcessBuilder processBuilder = new ProcessBuilder("python3",python_path,"--image",image_path);
        try {
            Process process = processBuilder.start();

            // 읽기 버퍼 설정
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();

            // 스트림에서 출력 읽기
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            // 프로세스가 완료될 때까지 대기
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                return "Python 스크립트 실행 성공. 출력:\n" + output.toString();
            } else {
                return "Python 스크립트 실행 중 오류 발생. 출력:\n" + output.toString();
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "오류 발생: " + e.getMessage();
        }
    }

    @Async
    @PostMapping("/analyze")
    public String faceAnalyze(@RequestParam("url") String url) {
        try {
            System.out.println(url);
            String image_path = faceAnalyzeService.getImageUrl(url);
            faceAnalyzeService.deleteImage(image_path);
            return faceAnalyzeService.runAnalyzePython(image_path);
        }catch (IOException | InterruptedException e){
            return e.getMessage();
        }
    }

}
