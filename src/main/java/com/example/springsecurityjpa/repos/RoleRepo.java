package com.example.springsecurityjpa.repos;

import com.example.springsecurityjpa.entity.Roles;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

public interface RoleRepo extends JpaRepositoryImplementation<Roles, Long> {
    Roles findRoleByRoleName(String roleName);
}
