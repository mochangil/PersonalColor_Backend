package Mo.PersonalColorBackend.repository;

import Mo.PersonalColorBackend.entity.BackgroundColor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BackgroundColorRepository extends JpaRepository<BackgroundColor,Long> {
}
