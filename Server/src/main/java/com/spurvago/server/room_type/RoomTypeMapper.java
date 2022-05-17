package com.spurvago.server.room_type;

import com.spurvago.database.RoomType;
import com.spurvago.server.room_type.models.RoomTypeVM;
import org.springframework.stereotype.Component;

@Component
public record RoomTypeMapper() {
    RoomTypeVM mapToVM(RoomType src) {
        RoomTypeVM dest = new RoomTypeVM();

        dest.setId(src.getId());
        dest.setType(src.getType());
        dest.setPrice(src.getPrice());

        return dest;
    }

    RoomType mapToEntity(RoomType dest, RoomType src) {
        dest.setType(src.getType());
        dest.setPrice(src.getPrice());

        return dest;
    }
}
