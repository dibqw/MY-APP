package com.example.springsecurityjpa.controllers;

import com.example.springsecurityjpa.entity.User;
import com.example.springsecurityjpa.exeptions.UserAlreadyExistsException;
import com.example.springsecurityjpa.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    UserDetailsServiceImpl userService;

    @PostMapping("/user")
    public ResponseEntity<String> createUser(@ModelAttribute User user) {
        try {
            userService.createUser(user.getUsername(), user.getPassword());
            return ResponseEntity.ok("User creted successfully");
        } catch (UserAlreadyExistsException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/user")
    public ResponseEntity<String> deleteUser(@RequestParam(name = "username") String username) {
        try {
            userService.deleteUser(username);
            return new ResponseEntity<>("ok", HttpStatus.ACCEPTED);
        } catch (UsernameNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
