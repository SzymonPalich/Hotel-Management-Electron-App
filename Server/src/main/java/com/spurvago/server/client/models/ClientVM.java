package com.spurvago.server.client.models;

import lombok.Getter;
import lombok.Setter;

/**
 * Model wyświetlania klientów
 */
@Getter
@Setter
public class ClientVM {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    public ClientVM() {
    }

    public ClientVM(Long id, String firstName, String lastName, String email, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
