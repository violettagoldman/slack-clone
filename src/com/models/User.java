package com.models;

import java.time.LocalDateTime;


public class User {
    private String username;
    private String email;
    private LocalDateTime createdAt;
    private String password;

    public User(String username, String email,String password) {
        this.username = username;
        this.email = email;
        this.password=password;
        this.createdAt = java.time.LocalDateTime.now();
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getPassword() {
        return password;
    }
}
