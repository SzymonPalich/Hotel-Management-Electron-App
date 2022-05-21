package com.spurvago.server.client;

import com.spurvago.components.ListPaginated;
import com.spurvago.database.Client;
import com.spurvago.server.client.models.ClientFM;
import com.spurvago.server.client.models.ClientVM;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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

    void mapToEntity(Client dest, ClientFM src) {
        dest.setFirstName(src.getFirstName());
        dest.setLastName(src.getLastName());
        dest.setEmail(src.getEmail());
        dest.setPhoneNumber(src.getPhoneNumber());
    }

    Client mapToEntity(ClientFM src) {
        Client dest = new Client();
        dest.setFirstName(src.getFirstName());
        dest.setLastName(src.getLastName());
        dest.setEmail(src.getEmail());
        dest.setPhoneNumber(src.getPhoneNumber());

        return dest;
    }

    List<ClientVM> mapToList(List<Client> srcList) {
        List<ClientVM> destList = new ArrayList<>();
        for (Client srcEntity : srcList) {
            destList.add(mapToVM(srcEntity));
        }

        return destList;
    }
}
