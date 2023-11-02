package Mo.PersonalColorBackend.dto;


import Mo.PersonalColorBackend.entity.PersonalColor;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonalColorDto {

    private Long id;
    private String tone;
    private String type;
    private String personalColorImg;
    private Integer frequency;

    public PersonalColorDto(PersonalColor personalColor){
        this.id = personalColor.getId();
        this.tone = personalColor.getTone();
        this.type = personalColor.getType();
        this.personalColorImg = personalColor.getPersonalColorImg();
        this.frequency = personalColor.getFrequency();
    }

    public PersonalColor toEntity(){
        return PersonalColor.builder()
                .id(id)
                .tone(tone)
                .type(type)
                .personalColorImg(personalColorImg)
                .frequency(frequency)
                .build();
    }
}
