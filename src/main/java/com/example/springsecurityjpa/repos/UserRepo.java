package com.example.springsecurityjpa.repos;

import com.example.springsecurityjpa.entity.User;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import java.util.Optional;

public interface UserRepo extends JpaRepositoryImplementation<User, Long> {
    Optional<User> findByUsername(String username);

    }
