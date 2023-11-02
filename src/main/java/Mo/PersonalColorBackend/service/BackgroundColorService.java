package Mo.PersonalColorBackend.service;

import Mo.PersonalColorBackend.dto.BackgroundColorDto;
import Mo.PersonalColorBackend.dto.FrameDto;
import Mo.PersonalColorBackend.entity.BackgroundColor;
import Mo.PersonalColorBackend.entity.Frame;
import Mo.PersonalColorBackend.repository.BackgroundColorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BackgroundColorService {
    private final BackgroundColorRepository backgroundColorRepository;

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
}
