package Mo.PersonalColorBackend.dto;

import Mo.PersonalColorBackend.entity.BackgroundColor;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BackgroundColorDto {

    private Long id;
    private String color;
    private String colorImg;

    public BackgroundColorDto(BackgroundColor backgroundColor){
        this.id = backgroundColor.getId();
        this.color = backgroundColor.getColor();
        this.colorImg = backgroundColor.getColorImg();
    }

    public BackgroundColor toEntity(){
        return new BackgroundColor().builder()
                .id(id)
                .color(color)
                .colorImg(colorImg)
                .build();
    }
}
