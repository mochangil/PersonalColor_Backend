package Mo.PersonalColorBackend.service;

import Mo.PersonalColorBackend.dto.FrameDto;
import Mo.PersonalColorBackend.dto.PersonalColorDto;
import Mo.PersonalColorBackend.entity.Frame;
import Mo.PersonalColorBackend.repository.FrameRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class FrameService {
    private final FrameRepository frameRepository;

    @Transactional
    public Long save(FrameDto frameDto){
        return frameRepository.save(frameDto.toEntity()).getId();
    }

    @Transactional
    public FrameDto findById(Long id){
        Frame frame = frameRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 프레임이 없습니다."));

        System.out.println(frame);
        return new FrameDto(frame);
    }

    @Transactional
    public Long update(FrameDto frameDto){
        Frame frame = frameRepository.findById(frameDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 프레임이 없습니다."));
        frame.update(frameDto.getFrameImg(),frameDto.getFrameName());
        return frame.getId();
    }
}
