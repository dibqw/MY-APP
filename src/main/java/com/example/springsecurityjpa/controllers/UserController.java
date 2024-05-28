package com.example.springsecurityjpa.controllers;

import com.example.springsecurityjpa.entity.User;
import com.example.springsecurityjpa.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @Autowired
    UserDetailsServiceImpl userService;

    @PostMapping("/create-user")
    public ResponseEntity<String> createUser(@ModelAttribute User user) {
        try {
            userService.createUser(user.getUsername(), user.getPassword());
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
        return ResponseEntity.ok("User creted successfully");
    }
}
