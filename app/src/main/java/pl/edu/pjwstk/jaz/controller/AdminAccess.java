package pl.edu.pjwstk.jaz.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AdminAccess {

    @PreAuthorize("hasRole('Admin')")
    @GetMapping("/auth0/admin")
    public String Admin() {
        return "Admin restricted resource";
    }
}