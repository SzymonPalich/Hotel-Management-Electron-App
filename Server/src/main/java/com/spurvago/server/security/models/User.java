package com.spurvago.server.security.models;


public class User {
    public UserId id;
    public String firstName;
    public String lastName;
    public String email;
    public UserRole role;

    public User(int id, String firstName, String lastName, String email, String role) {
        this.id = new UserId(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = new UserRole(role);
    }
}
