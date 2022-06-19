package com.spurvago.server.accommodation;

import com.spurvago.components.Validator;
import com.spurvago.database.Room;
import com.spurvago.server.accommodation.models.AccommodationFM;
import com.spurvago.server.client.ClientRepository;
import com.spurvago.server.room.RoomRepository;
import com.spurvago.server.room.RoomStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

/**
 * Walidator rezerwacji
 */
@Component
public class AccommodationValidator extends Validator {
    private final RoomRepository roomRepository;
    private final ClientRepository clientRepository;

    @Autowired
    public AccommodationValidator(RoomRepository roomRepository, ClientRepository clientRepository) {
        this.roomRepository = roomRepository;
        this.clientRepository = clientRepository;
    }

    public boolean validate(AccommodationFM model) {
        if (!clientRepository.existsById(model.getClientId())) {
            return false;
        }
        if (!roomRepository.existsById(model.getRoomId())) {
            return false;
        }
        Optional<Room> optionalRoom = roomRepository.findById(model.getRoomId());
        if (optionalRoom.isEmpty()) {
            return false;
        }
        Date d = new Date();
        d.setHours(0);
        d.setMinutes(0);
        if (model.getStartDate().before(d)) {
            return false;
        }
        return !model.getStartDate().after(model.getEndDate());
    }
}
