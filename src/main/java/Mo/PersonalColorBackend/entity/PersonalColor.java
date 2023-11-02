package Mo.PersonalColorBackend.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;


@Builder
@Entity
@Getter
@NoArgsConstructor
public class PersonalColor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String tone;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String personalColorImg;

    @Column(nullable = true)
    private Integer frequency;

    public PersonalColor(Long id, String tone, String type, String personalColorImg, Integer frequency){
        this.id = id;
        this.tone = tone;
        this.type = type;
        this.personalColorImg = personalColorImg;
        this.frequency = frequency;
    }


    public void update(String tone, String type, String personalImg){
        this.tone = tone;
        this.type = type;
        this.personalColorImg = personalColorImg;
    }
    public void resetFrequency(){
        this.frequency = 0;
    }


}

