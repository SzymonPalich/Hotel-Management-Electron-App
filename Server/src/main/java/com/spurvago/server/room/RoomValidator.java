package com.spurvago.server.room;

import com.spurvago.components.Validator;
import com.spurvago.server.room.models.RoomFM;
import com.spurvago.server.room_type.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Walidator pokojów
 */
@Component
public class RoomValidator extends Validator {
    private final RoomTypeRepository roomTypeRepository;

    @Autowired
    public RoomValidator(RoomTypeRepository roomTypeRepository) {
        this.roomTypeRepository = roomTypeRepository;
    }

    public boolean validate(RoomFM model) {
        if (!roomTypeRepository.existsById(model.getRoomTypeId())) {
            return false;
        }
        if (!(model.getRoomNumber() > 0) || !(intOverflow(model.getRoomNumber()))) {
            return false;
        }
        return model.getStatus() > 0 && model.getStatus() < 7;
    }
}
