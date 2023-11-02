package Mo.PersonalColorBackend.controller;

import Mo.PersonalColorBackend.dto.PersonalColorDto;
import Mo.PersonalColorBackend.service.PersonalColorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/personalColor")
public class PersonalColorController {

    private final PersonalColorService personalColorService;

    @PostMapping
    public Long save(@RequestBody PersonalColorDto requestDto){
        return personalColorService.save(requestDto);
    }


    @PostMapping("/result")
    public PersonalColorDto findById(@RequestBody Long id){
        return personalColorService.findById(id);
    }

    @PostMapping("/update")
    public Long update(@RequestBody PersonalColorDto personalColorDto){
        return personalColorService.update(personalColorDto);
    }
//    @PostMapping("/result")
//    @ResponseBody
//    public List<Map<String,Object>> personalColorResult(){
//        return
//
//    }
}
