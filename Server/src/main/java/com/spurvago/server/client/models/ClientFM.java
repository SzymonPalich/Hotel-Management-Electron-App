package com.spurvago.server.client.models;

import com.spurvago.components.Utils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientFM {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
