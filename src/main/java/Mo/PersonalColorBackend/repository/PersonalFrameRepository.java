package Mo.PersonalColorBackend.repository;

import Mo.PersonalColorBackend.entity.Frame;
import Mo.PersonalColorBackend.entity.PersonalFrame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface PersonalFrameRepository extends JpaRepository<PersonalFrame,Long> {
    List<PersonalFrame> findByPersonalColorId(Long personalColorId);
}
