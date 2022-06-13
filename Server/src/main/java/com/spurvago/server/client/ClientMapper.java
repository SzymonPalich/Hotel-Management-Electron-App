package com.spurvago.server.client;

import com.spurvago.database.Client;
import com.spurvago.server.client.models.ClientFM;
import com.spurvago.server.client.models.ClientSelect;
import com.spurvago.server.client.models.ClientVM;
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

    ClientSelect mapToSelect(Client src) {
        ClientSelect dest = new ClientSelect();

        dest.setId(src.getId());
        String label = src.getFirstName() + " " + src.getLastName();
        dest.setClientLabel(label);
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

    List<ClientSelect> mapToSelectList(List<Client> srcList) {
        List<ClientSelect> destList = new ArrayList<>();
        for (Client srcEntity : srcList) {
            destList.add(mapToSelect(srcEntity));
        }

        return destList;
    }
}
