package com.example.springsecurityjpa.services;

import com.example.springsecurityjpa.entity.Roles;
import com.example.springsecurityjpa.entity.User;
import com.example.springsecurityjpa.repos.UserRepo;
import com.example.springsecurityjpa.repos.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepo repo;
    @Autowired
    RoleRepo rolesRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRoles())
                .build();
    }
    public void createUser(String username, String password) throws Exception {
        if (repo.findByUsername(username).isPresent()) {
            throw new Exception("User name alredy exist.");
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(password);

        Roles role = rolesRepo.findRoleByRoleName("USER");

        User newUser = new User(username, encryptedPassword, role);
        repo.save(newUser);
    }
}
