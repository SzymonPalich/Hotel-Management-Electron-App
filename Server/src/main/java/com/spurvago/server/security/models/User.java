package com.spurvago.server.security.models;

import lombok.Getter;
import lombok.Setter;

/**
 * Model użytkownika
 */
@Getter
@Setter
public class User {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String role;

    public User(long id, String firstName, String lastName, String email, String role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
    }
}