package Mo.PersonalColorBackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonalFrame {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name="frameId")
    private Frame frame;

    @ManyToOne
    @JoinColumn(name="personalColorId")
    private PersonalColor personalColor;
}
