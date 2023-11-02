package Mo.PersonalColorBackend.dto;

import Mo.PersonalColorBackend.entity.BackgroundColor;
import Mo.PersonalColorBackend.entity.PersonalBackgroundColor;
import Mo.PersonalColorBackend.entity.PersonalColor;
import lombok.*;
import lombok.extern.slf4j.Slf4j;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonalBackgroundColorDto {
    private Long id;
    private PersonalColor personalColor;
    private BackgroundColor backgroundColor;

    public PersonalBackgroundColorDto(PersonalBackgroundColor personalBackgroundColor){
        this.id = personalBackgroundColor.getId();
        this.personalColor = personalBackgroundColor.getPersonalColor();
        this.backgroundColor = personalBackgroundColor.getBackgroundColor();
    }

    public PersonalBackgroundColor toEntity(){
        return PersonalBackgroundColor.builder()
                .id(id)
                .personalColor(personalColor)
                .backgroundColor(backgroundColor)
                .build();
    }
}
