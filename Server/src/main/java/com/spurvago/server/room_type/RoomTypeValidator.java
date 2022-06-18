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
        if (!haveLength(model.getType(),0, 50)) {
            return false;
        }
        if (!correctDecimal(model.getPrice(), 8, 2)) {
            return false;
        }
        if (model.getPrice().equals(0)) {
            return false;
        }
        return true;
    }
}
