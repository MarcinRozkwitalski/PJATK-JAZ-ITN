package pl.edu.pjwstk.jaz.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import pl.edu.pjwstk.jaz.database.UserEntity;
import pl.edu.pjwstk.jaz.database.UserService;
import pl.edu.pjwstk.jaz.domain.User;
import pl.edu.pjwstk.jaz.domain.Users;
import pl.edu.pjwstk.jaz.request.RegisterRequest;

import javax.persistence.NoResultException;

@RestController
public class RegisterController {

    private final Users users;
    private final UserService userService;

    public RegisterController(Users users, UserService userService) {
        this.users = users;
        this.userService = userService;
    }

    @PostMapping("register")
    public void register(@RequestBody RegisterRequest registerRequest) {
        String permission = "User";

        Number sum = userService.users();
        int usersNumber = sum.intValue();
        System.out.println(sum);

        if(usersNumber == 0) {
            permission = "Admin";
            User user = new User(registerRequest.getUsername(), registerRequest.getPassword());
            user.addAuthorities(permission);
            users.add(user);

            userService.saveUser(registerRequest.getUsername(),registerRequest.getPassword(),user.getAuthorities());
            UserEntity singleResult = userService.findUserByUsername(registerRequest.getUsername());
            System.out.println("added admin " + singleResult);
        }
        else if (!userService.userExist(registerRequest.getUsername()) ){
            User user = new User(registerRequest.getUsername(), registerRequest.getPassword());
            user.addAuthorities(permission);
            users.add(user);

            userService.saveUser(registerRequest.getUsername(),registerRequest.getPassword(),user.getAuthorities());
            UserEntity singleResult = userService.findUserByUsername(registerRequest.getUsername());
            System.out.println("added user " + singleResult);
        }

    }

    @GetMapping("/register")
    public java.util.ArrayList<User> usersInHashMap() {
        return users.usersInHashMap();
    }
}