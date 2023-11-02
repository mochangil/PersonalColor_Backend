package Mo.PersonalColorBackend.repository;

import Mo.PersonalColorBackend.entity.PersonalBackgroundColor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonalBackgroundColorRepository extends JpaRepository<PersonalBackgroundColor,Long> {
    List<PersonalBackgroundColor> findByPersonalColorId(Long personalColorId);
}
