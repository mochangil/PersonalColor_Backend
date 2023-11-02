package Mo.PersonalColorBackend.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class FaceAnalyzeController {

    @PostMapping
    public Long personalColorAnalyze(@RequestBody String img){
        //analyze face info
        Long id = Long.valueOf(1);
        return (id);
    }

}
