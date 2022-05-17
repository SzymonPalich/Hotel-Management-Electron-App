package com.spurvago.server.client.models;

import com.spurvago.database.Client;
import lombok.Getter;
import lombok.Setter;

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

    public ClientVM(Client entity) {
        this.id = entity.getId();
        this.firstName = entity.getFirstName();
        this.lastName = entity.getLastName();
        this.email = entity.getEmail();
        this.phoneNumber = entity.getPhoneNumber();
    }
}
