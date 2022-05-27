package com.spurvago.server.room_type;

import com.spurvago.components.Validator;
import com.spurvago.server.room_type.RoomTypeRepository;
import com.spurvago.server.room_type.models.RoomTypeFM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class RoomTypeValidator extends Validator {
    private final RoomTypeRepository roomTypeRepository;

    @Autowired
    public RoomTypeValidator(RoomTypeRepository roomTypeRepository) {
        this.roomTypeRepository = roomTypeRepository;
    }

    public boolean validate(RoomTypeFM model) {
        if (!roomTypeRepository.existsById(model.getId())) {
            return false;
        }
        if (!haveLength(model.getType(), 50)) {
            return false;
        }
        return correctDecimal(model.getPrice(), 8, 2);
    }
}
