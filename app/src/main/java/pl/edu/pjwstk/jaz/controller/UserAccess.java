package pl.edu.pjwstk.jaz.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.jaz.domain.User;

@RestController
public class UserAccess {

    @PreAuthorize("hasRole('User')")
    @GetMapping("/auth0/users")
    public String users() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(user.getName() + " " + user.getLastName());
        return "User restricted resource";
    }
}