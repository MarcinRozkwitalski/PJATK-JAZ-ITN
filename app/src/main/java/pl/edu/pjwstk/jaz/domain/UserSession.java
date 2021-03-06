package pl.edu.pjwstk.jaz.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class UserSession {
    private boolean isLogged = false;

    public void logIn(){
        isLogged = true;
    }
    public void logOut(){
        isLogged = false;
    }
    public boolean isLoggedIn() {
        return isLogged;
    }
    //tutaj jakas zmienna informacja
    // która pozwoli okreslic czy uzytkownik jest zalogowany
    //metody do zarzadzania
}