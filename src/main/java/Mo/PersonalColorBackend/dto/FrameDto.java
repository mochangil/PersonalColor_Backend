package Mo.PersonalColorBackend.dto;

import Mo.PersonalColorBackend.entity.Frame;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FrameDto {

    private Long id;
    private String frameName;
    private String frameImg;

    public FrameDto(Frame frame){
        this.id = frame.getId();
        this.frameImg = frame.getFrameImg();
        this.frameName = frame.getFrameName();
    }

    public Frame toEntity(){
        return Frame.builder()
                .id(id)
                .frameImg(frameImg)
                .frameName(frameName)
                .build();
    }
}
