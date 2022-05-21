package com.spurvago.server.accommodation;

import com.spurvago.database.Accommodation;
import com.spurvago.server.accommodation.models.AccommodationFM;
import com.spurvago.server.accommodation.models.AccommodationVM;
import com.spurvago.server.client.ClientRepository;
import com.spurvago.server.room.RoomRepository;
import org.springframework.stereotype.Component;

@Component
public record AccommodationMapper(ClientRepository clientRepository, RoomRepository roomRepository) {

    Accommodation mapToEntity(AccommodationFM src) {
        Accommodation dest = new Accommodation();
        dest.setRoom(roomRepository.findById(src.getRoomId()));
        dest.setClient(clientRepository.findById(src.getClientId()));
        dest.setStartDate(src.getStartDate());
        dest.setEndDate(src.getEndDate());
        dest.setReservationOnly(src.getReservationOnly());

        return dest;
    }

    void mapToEntity(Accommodation dest, AccommodationFM src) {
        dest.setRoom(roomRepository.findById(src.getRoomId()));
        dest.setClient(clientRepository.findById(src.getClientId()));
        dest.setStartDate(src.getStartDate());
        dest.setEndDate(src.getEndDate());
        dest.setReservationOnly(src.getReservationOnly());
    }

    AccommodationVM mapToVM(Accommodation src) {
        AccommodationVM dest = new AccommodationVM();

        dest.setId(src.getId());
        dest.setRoomNumber(src.getRoom().getRoomNumber());
        dest.setRoomType(src.getRoom().getRoomType().getType());
        dest.setRoomStatus(src.getRoom().getStatus());
        dest.setClientFirstName(src.getClient().getFirstName());
        dest.setClientLastName(src.getClient().getLastName());
        dest.setStartDate(src.getStartDate());
        dest.setEndDate(src.getEndDate());
        dest.setReservationOnly(src.getReservationOnly());

        return dest;
    }
}
