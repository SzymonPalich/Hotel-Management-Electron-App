package com.spurvago.server.accommodation;

import com.spurvago.database.Accommodation;
import com.spurvago.database.Client;
import com.spurvago.database.Room;
import com.spurvago.server.accommodation.models.AccommodationFM;
import com.spurvago.server.accommodation.models.AccommodationVM;
import com.spurvago.server.client.ClientRepository;
import com.spurvago.server.room.RoomRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Mapper do rezerwacji
 * @param clientRepository
 * @param roomRepository
 */
@Component
public record AccommodationMapper(ClientRepository clientRepository, RoomRepository roomRepository) {

    Accommodation mapToEntity(AccommodationFM src) {
        Accommodation dest = new Accommodation();
        Optional<Room> optionalRoom = roomRepository.findById(src.getRoomId());
        Room room;
        if (optionalRoom.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        room = optionalRoom.get();
        dest.setRoom(room);
        Optional<Client> optionalClient = clientRepository.findById(src.getClientId());
        Client client;
        if (optionalClient.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        client = optionalClient.get();
        dest.setClient(client);
        dest.setStartDate(src.getStartDate());
        dest.setEndDate(src.getEndDate());

        return dest;
    }

    void mapToEntity(Accommodation dest, AccommodationFM src) {
        Optional<Room> optionalRoom = roomRepository.findById(src.getRoomId());
        Room room;
        if (optionalRoom.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        room = optionalRoom.get();
        dest.setRoom(room);
        Optional<Client> optionalClient = clientRepository.findById(src.getClientId());
        Client client;
        if (optionalClient.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        client = optionalClient.get();
        dest.setClient(client);
        dest.setStartDate(src.getStartDate());
        dest.setEndDate(src.getEndDate());
    }

    AccommodationVM mapToVM(Accommodation src) {
        AccommodationVM dest = new AccommodationVM();

        dest.setId(src.getId());
        dest.setRoomId(src.getRoom().getId());
        dest.setRoomNumber(src.getRoom().getRoomNumber());
        dest.setRoomType(src.getRoom().getRoomType().getType());
        dest.setClientId(src.getClient().getId());
        dest.setClientFirstName(src.getClient().getFirstName());
        dest.setClientLastName(src.getClient().getLastName());
        dest.setStartDate(src.getStartDate());
        dest.setEndDate(src.getEndDate());

        return dest;
    }

    List<AccommodationVM> mapToList(List<Accommodation> srcList) {
        List<AccommodationVM> destList = new ArrayList<>();
        for (Accommodation srcEntity : srcList) {
            destList.add(mapToVM(srcEntity));
        }

        return destList;
    }
}
