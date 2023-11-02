package Mo.PersonalColorBackend.controller;

import Mo.PersonalColorBackend.dto.FrameDto;
import Mo.PersonalColorBackend.dto.PersonalColorDto;
import Mo.PersonalColorBackend.service.FrameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/frame")
public class FrameController {
    private final FrameService frameService;

    @PostMapping
    public Long save(@RequestBody FrameDto frameDto){
        return frameService.save(frameDto);
    }


    @PostMapping("/find")
    public FrameDto findById(@RequestBody Long id){
        return frameService.findById(id);
    }

    @PostMapping("/update")
    public Long update(@RequestBody FrameDto frameDto){
        return frameService.update(frameDto);
    }

    @PostMapping("/result")
    public List<FrameDto> findByPersonalColorId(@RequestBody HashMap<String, Long> map) {
        return frameService.findByPersonalColorId((map.get("id")));
    }
}
