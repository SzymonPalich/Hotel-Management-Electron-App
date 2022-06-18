package com.spurvago.server.client.models;

import lombok.Getter;
import lombok.Setter;

/**
 * Model formularza klientów
 */
@Getter
@Setter
public class ClientFM {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    public ClientFM(String firstName, String lastName, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

}
