package com.spurvago.server.room;

import com.spurvago.database.Room;
import com.spurvago.server.room.models.RoomFM;
import com.spurvago.server.room.models.RoomVM;
import com.spurvago.server.room_type.RoomTypeRepository;
import org.springframework.stereotype.Component;

@Component
public record RoomMapper(RoomTypeRepository roomTypeRepository) {
    RoomVM mapToVM(Room src) {
        RoomVM dest = new RoomVM();

        dest.setId(src.getId());
        dest.setRoomNumber(src.getRoomNumber());
        dest.setRoomType(src.getRoomType().getType());
        dest.setStatus(src.getStatus());

        return dest;
    }

    Room mapToEntity(Room dest, RoomFM src) {
        dest.setRoomNumber(src.getRoomNumber());
        dest.setRoomType(roomTypeRepository.findById(src.getRoomTypeId()));
        dest.setStatus(src.getStatus());

        return dest;
    }

    Room mapToEntity(RoomFM src) {
        Room dest = new Room();
        dest.setRoomNumber(src.getRoomNumber());
        dest.setRoomType(roomTypeRepository.findById(src.getRoomTypeId()));
        dest.setStatus(src.getStatus());

        return dest;
    }
}
