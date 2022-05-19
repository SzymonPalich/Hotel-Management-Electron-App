package com.spurvago.server.client;

import com.spurvago.database.Client;
import com.spurvago.server.client.models.ClientFM;
import com.spurvago.server.client.models.ClientVM;
import org.springframework.stereotype.Component;

@Component
public record ClientMapper() {
    ClientVM mapToVM(Client src) {
        ClientVM dest = new ClientVM();
        dest.setId(src.getId());
        dest.setFirstName(src.getFirstName());
        dest.setLastName(src.getLastName());
        dest.setEmail(src.getEmail());
        dest.setPhoneNumber(src.getPhoneNumber());

        return dest;
    }

    Client mapToEntity(Client dest, ClientFM src) {
        dest.setFirstName(src.getFirstName());
        dest.setLastName(src.getLastName());
        dest.setEmail(src.getEmail());
        dest.setPhoneNumber(src.getPhoneNumber());

        return dest;
    }

    Client mapToEntity(ClientFM src) {
        Client dest = new Client();
        dest.setFirstName(src.getFirstName());
        dest.setLastName(src.getLastName());
        dest.setEmail(src.getEmail());
        dest.setPhoneNumber(src.getPhoneNumber());

        return dest;
    }


}
