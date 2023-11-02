package Mo.PersonalColorBackend.service;

import Mo.PersonalColorBackend.dto.BackgroundColorDto;
import Mo.PersonalColorBackend.dto.FrameDto;
import Mo.PersonalColorBackend.entity.BackgroundColor;
import Mo.PersonalColorBackend.entity.Frame;
import Mo.PersonalColorBackend.entity.PersonalBackgroundColor;
import Mo.PersonalColorBackend.repository.BackgroundColorRepository;
import Mo.PersonalColorBackend.repository.PersonalBackgroundColorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BackgroundColorService {
    private final BackgroundColorRepository backgroundColorRepository;
    private final PersonalBackgroundColorRepository personalBackgroundColorRepository;

    @Transactional
    public Long save(BackgroundColorDto backgroundColorDto){
        return backgroundColorRepository.save(backgroundColorDto.toEntity()).getId();
    }

    @Transactional
    public BackgroundColorDto findById(Long id){
        BackgroundColor backgroundColor = backgroundColorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 배경색이 없습니다."));

        System.out.println(backgroundColor);
        return new BackgroundColorDto(backgroundColor);
    }

    @Transactional
    public Long update(BackgroundColorDto backgroundColorDto){
        BackgroundColor backgroundColor = backgroundColorRepository.findById(backgroundColorDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 배경색이 없습니다."));
        backgroundColor.update(backgroundColorDto.getColor(),backgroundColorDto.getColorImg());
        return backgroundColor.getId();
    }

    @Transactional(readOnly = true)
    public List<BackgroundColorDto> findByPersonalColorId(Long id){
        List<PersonalBackgroundColor> backgroundColorList = personalBackgroundColorRepository.findByPersonalColorId(id);
        List<BackgroundColorDto> backgroundColorDtoList = backgroundColorList.stream()
                .map(PersonalBackgroundColor::getBackgroundColor)
                .map(BackgroundColorDto::new)
                .collect(Collectors.toList());
        return backgroundColorDtoList;

    }
}
