package pl.edu.pjwstk.jaz.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import pl.edu.pjwstk.jaz.repo.RoleRepository;
import pl.edu.pjwstk.jaz.repo.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Set;

@Repository
@Transactional
public class UserService {

    private UserRepository userRepository;
    private final EntityManager entityManager;


    @Autowired
    public UserService(UserRepository userRepository, EntityManager entityManager) {
        this.userRepository = userRepository;
        this.entityManager = entityManager;
    }

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    public UserService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void saveUser(String username, String password, Set<String> authorities) {
        UserEntity userEntity = new UserEntity(username,passwordEncoder.encode(password));

        for (String auto: authorities){
            System.out.println("role " + auto);
        }

        for(String roles: authorities){
            RoleEntity roleEntity = new RoleEntity(roles);
            userEntity.getRoles().add(roleEntity);
        }

        this.userRepository.save(userEntity);
    }

    public UserEntity findUserByUsername(String username) {
        return entityManager.createQuery("select ue from UserEntity ue where ue.username = :username", UserEntity.class)
                .setParameter("username", username)//username
                .getSingleResult();
    }

    public Number users() {
        Query q = entityManager.createQuery("SELECT count(ue) FROM UserEntity ue");
        Number result = (Number) q.getSingleResult();
        return result;
    }

    public boolean userExist(String username) {
        UserEntity name = userRepository.findByUsername(username).orElseGet(UserEntity::new);
        if (name.getUsername() == null)
            return false;
        return true;
    }

    public boolean passwordFindUserByUsername(String usernameInput,String password) {
        try {
            UserEntity name = userRepository.findByUsername(usernameInput).orElseGet(UserEntity::new);
            if (getPasswordEncoder().matches(password,name.getPassword()) )
                return true;
            return false;
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        return false;
    }
}