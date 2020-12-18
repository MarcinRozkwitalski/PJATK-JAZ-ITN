package pl.edu.pjwstk.jaz.examples;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class ExampleService {
    private final HttpServletRequest httpServletRequest;

    @Autowired
    public ExampleService(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    //public String getMyHeader() {
    //   return httpServletRequest.getHeader()
    //}


}