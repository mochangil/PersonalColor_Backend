package Mo.PersonalColorBackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class PersonalBackgroundColor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="backgroundId")
    private BackgroundColor backgroundColor;

    @ManyToOne
    @JoinColumn(name="personalColorId")
    private PersonalColor personalColor;
}
