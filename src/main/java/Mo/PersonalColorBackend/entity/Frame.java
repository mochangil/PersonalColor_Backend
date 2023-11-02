package Mo.PersonalColorBackend.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Builder
@Getter
@Entity
@NoArgsConstructor
public class Frame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String frameImg;

    @Column(nullable = false)
    private String frameName;

    public Frame(Long id, String frameImg, String frameName){
        this.id = id;
        this.frameImg = frameImg;
        this.frameName = frameName;
    }

    public void update(String frameImg, String frameName){
        this.frameImg = frameImg;
        this.frameName = frameName;
    }

}
