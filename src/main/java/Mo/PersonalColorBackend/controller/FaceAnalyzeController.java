package Mo.PersonalColorBackend.controller;


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
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static org.springframework.http.HttpHeaders.SET_COOKIE;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/faceAnalyze")
public class FaceAnalyzeController {

    //@PostMapping
    @GetMapping
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

}
