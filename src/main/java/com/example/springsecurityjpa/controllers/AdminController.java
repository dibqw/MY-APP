package com.example.springsecurityjpa.controllers;

import com.example.springsecurityjpa.exeptions.UserAlreadyAdminException;
import com.example.springsecurityjpa.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {

    @Autowired
    UserDetailsServiceImpl userService;

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/admin-user")
    public ResponseEntity<String> addUserToAdmin(@RequestParam(name = "username") String username) {
        try {
            userService.makeUserAdmin(username);
            return ResponseEntity.ok("User has been added to the admin users.");
        } catch (UsernameNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
        } catch (UserAlreadyAdminException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
        }
    }
}
