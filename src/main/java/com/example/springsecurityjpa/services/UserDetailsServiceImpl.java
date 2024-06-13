package com.example.springsecurityjpa.services;

import com.example.springsecurityjpa.entity.Roles;
import com.example.springsecurityjpa.entity.User;
import com.example.springsecurityjpa.exeptions.UserAlreadyAdminException;
import com.example.springsecurityjpa.exeptions.UserAlreadyExistsException;
import com.example.springsecurityjpa.repos.UserRepo;
import com.example.springsecurityjpa.repos.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Arrays;

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

    public void createUser(String username, String password) {
        if (repo.findByUsername(username).isPresent()) {
            throw new UserAlreadyExistsException("User with name " + username + " alredy exist.");
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(password);

        Roles role = rolesRepo.findRoleByRoleName("USER");

        User newUser = new User(username, encryptedPassword, role);
        repo.save(newUser);
    }

    public void makeUserAdmin(String username) {
        User user = repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        if (Arrays.asList(user.getRoles()).contains("ADMIN")) {
            throw new UserAlreadyAdminException("User with name " + username + " is alredy admin.");
        }
        Roles role = rolesRepo.findRoleByRoleName("ADMIN");
        user.addRole(role);

        repo.save(user);
    }

    public void deleteUser(String username) {
        User user = repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        repo.delete(user);
    }
}
