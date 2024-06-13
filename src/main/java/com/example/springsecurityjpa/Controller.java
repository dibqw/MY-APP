package com.example.springsecurityjpa;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {


    @GetMapping("/user")
    public String user() {
        return ("<h1>Welcome User</h1>");
    }

}
