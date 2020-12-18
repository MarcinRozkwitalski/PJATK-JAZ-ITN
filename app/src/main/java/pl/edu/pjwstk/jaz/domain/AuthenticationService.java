package pl.edu.pjwstk.jaz.domain;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pl.edu.pjwstk.jaz.database.RoleEntity;
import pl.edu.pjwstk.jaz.database.UserEntity;
import pl.edu.pjwstk.jaz.database.UserService;
import pl.edu.pjwstk.jaz.exception.UserDoesNotExistException;

import java.util.stream.Collectors;

@Component
public class AuthenticationService {

    final UserSession userSession;
    final UserService userService;
    final Users users;

    public AuthenticationService(UserSession userSession, UserService userService, Users users) {
        this.userSession = userSession;
        this.userService = userService;
        this.users = users;
    }

    public boolean login(String username, String password) {

        if (userService.userExist(username)){
            if ((userService.passwordFindUserByUsername(username,password))) {
                userSession.logIn();

                UserEntity userEntity = userService.findUserByUsername(username);
                User userFromDatabase = new User(userEntity.getUsername(),userEntity.getPassword(),userEntity.getRoles().stream().map(RoleEntity::getRole).collect(Collectors.toSet()));
                SecurityContextHolder.getContext().setAuthentication(new AppAuthentication(userFromDatabase));
                return true;
            }
        }
        return false;
    }
}