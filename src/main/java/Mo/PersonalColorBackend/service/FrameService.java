package Mo.PersonalColorBackend.service;

import Mo.PersonalColorBackend.dto.FrameDto;
import Mo.PersonalColorBackend.dto.PersonalColorDto;
import Mo.PersonalColorBackend.entity.Frame;
import Mo.PersonalColorBackend.entity.PersonalFrame;
import Mo.PersonalColorBackend.repository.FrameRepository;
import Mo.PersonalColorBackend.repository.PersonalFrameRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class FrameService {
    private final FrameRepository frameRepository;
    private final PersonalFrameRepository personalFrameRepository;

    @Transactional
    public Long save(FrameDto frameDto){
        return frameRepository.save(frameDto.toEntity()).getId();
    }

    @Transactional
    public FrameDto findById(Long id){
        Frame frame = frameRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 프레임이 없습니다."));

        //logging
        return new FrameDto(frame);
    }

    @Transactional
    public Long update(FrameDto frameDto){
        Frame frame = frameRepository.findById(frameDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 프레임이 없습니다."));
        frame.update(frameDto.getFrameImg(),frameDto.getFrameName());
        return frame.getId();
    }

    @Transactional(readOnly = true)
    public List<FrameDto> findByPersonalColorId(Long id){
        List<PersonalFrame> personalFrameList = personalFrameRepository.findByPersonalColorId(id);
        List<FrameDto> frameDtoList = personalFrameList.stream()
                .map(PersonalFrame::getFrame)
                .map(FrameDto::new)
                .collect(Collectors.toList());
        return frameDtoList;
    }
}
