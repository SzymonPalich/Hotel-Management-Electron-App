package com.spurvago.server.client.models;

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
}
