package com.example.springsecurityjpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    @JsonIgnore
    private boolean enabled;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Roles> roles;

    public User() {
    }

    public User(String username, String password, Roles role) {
        List<Roles> roles = new ArrayList<>();
        roles.add(role);
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.enabled = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String[] getRoles() {
        return roles.stream().map(Roles::getRole).toArray(String[]::new);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return enabled;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }


    // Implement UserDetails methods (getAuthorities, isEnabled, getUsername, getPassword, isAccountNonExpired, isAccountNonLocked, isCredentialsNonExpired)
}

