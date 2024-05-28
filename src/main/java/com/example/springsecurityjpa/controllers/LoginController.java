package com.example.springsecurityjpa.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/create-user")
    public String showCreateUserForm() {
        return "create-user";
    }
}
