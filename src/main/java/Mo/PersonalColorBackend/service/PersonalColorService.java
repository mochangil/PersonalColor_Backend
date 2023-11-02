package Mo.PersonalColorBackend.service;

import Mo.PersonalColorBackend.dto.PersonalColorDto;
import Mo.PersonalColorBackend.entity.PersonalColor;
import Mo.PersonalColorBackend.repository.PersonalColorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PersonalColorService {
    private final PersonalColorRepository personalColorRepository;
    @Transactional
    public Long save(PersonalColorDto personalColorDto){
        return personalColorRepository.save(personalColorDto.toEntity()).getId();
    }
    @Transactional(readOnly = true)
    public PersonalColorDto findById(Long id){
        PersonalColor personalColor = personalColorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 퍼스널 컬러가 없습니다."));

        System.out.println(personalColor);
        return new PersonalColorDto(personalColor);
    }

    @Transactional
    public Long update(PersonalColorDto personalColorDto){
        PersonalColor personalColor = personalColorRepository.findById(personalColorDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 퍼스널 컬러가 없습니다."));
        personalColor.update(personalColorDto.getTone(),personalColorDto.getType(),personalColorDto.getPersonalColorImg());

        return personalColor.getId();
    }




}
