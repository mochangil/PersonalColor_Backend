package Mo.PersonalColorBackend.entity;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Builder
@Getter
@Entity
@NoArgsConstructor
public class BackgroundColor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private String colorImg;

    public BackgroundColor(Long id, String color, String colorImg){
        this.id = id;
        this.color = color;
        this.colorImg = colorImg;
    }

    public void update(String color, String colorImg){
        this.color=color;
        this.colorImg=colorImg;
    }
}
