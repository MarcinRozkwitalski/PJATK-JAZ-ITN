package pl.edu.pjwstk.jaz;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;


public class User {
    private String name;
    private String lastName;
    private String username;
    private String password;
    private String permission;

    private Set<String> authorities;


    public User(String username, String password,String permission) {
        this.username = username;
        this.password = password;
        this.permission = permission;
        authorities = new HashSet<>();
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    public void addAuthorities(String permission){
        authorities.add(permission);
    }

    public String getName() {
        return name;
    }

    public String getPermission() { return permission; }

    public void setPermission(String permission) { this.permission = permission; }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}