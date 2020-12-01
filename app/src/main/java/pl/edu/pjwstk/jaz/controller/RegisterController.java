package pl.edu.pjwstk.jaz;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    private final Users users;

    public RegisterController(Users users) {
        this.users = users;
    }

    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest registerRequest) {
        String permission = "User";
        //zarejestrowac
        if(users.isEmpty()) {
            permission = "Admin";
            User user = new User(registerRequest.getUsername(), registerRequest.getPassword(),permission);
            user.addAuthorities(permission);
            users.add(user);
        }
        else {
            if (!users.nameExist(registerRequest.getUsername())){
                User user = new User(registerRequest.getUsername(), registerRequest.getPassword(), permission);
                user.addAuthorities(permission);
                users.add(user);
            }
        }
    }
    // remove
    @GetMapping("/register")
    public java.util.ArrayList<User> usersInHashMap() {
        return users.usersInHashMap();
    }
}