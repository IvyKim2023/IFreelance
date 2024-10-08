package com.IvyJin.IFreelance.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class AppUser {
    @Id
    private String username;
    private String password;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Users{" +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
