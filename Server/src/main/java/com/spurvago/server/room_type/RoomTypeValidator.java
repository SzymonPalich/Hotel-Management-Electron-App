package com.spurvago.server.room_type;

import com.spurvago.components.Validator;
import com.spurvago.server.room_type.models.RoomTypeFM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Walidator typów pokojów
 */
@Component
public class RoomTypeValidator extends Validator {
    private final RoomTypeRepository roomTypeRepository;

    @Autowired
    public RoomTypeValidator(RoomTypeRepository roomTypeRepository) {
        this.roomTypeRepository = roomTypeRepository;
    }

    public boolean validate(RoomTypeFM model) {
        if (!haveLength(model.getType(), 50)) {
            return false;
        }
        return correctDecimal(model.getPrice(), 8, 2);
    }
}
