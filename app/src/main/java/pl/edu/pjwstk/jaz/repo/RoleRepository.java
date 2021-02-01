package pl.edu.pjwstk.jaz.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pjwstk.jaz.database.RoleEntity;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity,Long> {
}