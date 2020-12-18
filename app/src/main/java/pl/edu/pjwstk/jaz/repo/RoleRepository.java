package pl.edu.pjwstk.jaz.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pjwstk.jaz.database.RoleEntity;
import pl.edu.pjwstk.jaz.database.UserEntity;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity ,Long> {
}