package pl.edu.pjwstk.jaz.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pjwstk.jaz.database.SectionEntity;
import java.util.Optional;

@Repository
public interface SectionRepository extends JpaRepository<SectionEntity,Long> {
    public Optional<SectionEntity> findByName(String name);
}