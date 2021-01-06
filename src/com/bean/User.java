package com.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class User implements Serializable {
    private long id;
    private String username;
    private String email;
    private Timestamp createdAt;
    private String password;
    private String icone;

    public User() {}

    public User(long id, String username, String email, String password, String icone) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.icone = icone;
        this.createdAt = new Timestamp(System.currentTimeMillis());;
    }

    public User(long id, String username, String email, String password, Timestamp createAt, String icone) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.icone = icone;
        this.createdAt = createAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIcone() {
        return icone;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", createdAt=" + createdAt +
                ", password='" + password + '\'' +
                ", icone='" + icone + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                username.equals(user.username) &&
                email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, createdAt, password, icone);
    }
}
