package Mo.PersonalColorBackend.controller;

import Mo.PersonalColorBackend.dto.BackgroundColorDto;
import Mo.PersonalColorBackend.entity.BackgroundColor;
import Mo.PersonalColorBackend.service.BackgroundColorService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/background")
public class BackgroundColorController {

    private final BackgroundColorService backgroundColorService;

    @PostMapping
    public Long save(BackgroundColorDto backgroundColorDto){
        return backgroundColorService.save(backgroundColorDto);
    }

    @PostMapping("/find")
    public BackgroundColorDto findById(@RequestBody Long id){
        return backgroundColorService.findById(id);
    }

    @PostMapping("/update")
    public Long update(@RequestBody BackgroundColorDto backgroundColorDto){
        return backgroundColorService.update(backgroundColorDto);
    }

    @PostMapping("/result")
    public List<BackgroundColorDto> findByPersonalColorId(@RequestBody HashMap<String, Long> map){
        return backgroundColorService.findByPersonalColorId(map.get("id"));
    }

}
