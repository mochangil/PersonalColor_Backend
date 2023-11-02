package Mo.PersonalColorBackend.controller;

import Mo.PersonalColorBackend.dto.BackgroundColorDto;
import Mo.PersonalColorBackend.service.BackgroundColorService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/result")
    public BackgroundColorDto findById(@RequestBody Long id){
        return backgroundColorService.findById(id);
    }

    @PostMapping("/update")
    public Long update(@RequestBody BackgroundColorDto backgroundColorDto){
        return backgroundColorService.update(backgroundColorDto);
    }

}
