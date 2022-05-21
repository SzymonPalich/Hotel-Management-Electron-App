package com.spurvago.server.room;

import com.spurvago.database.Room;
import com.spurvago.server.room.models.RoomFM;
import com.spurvago.server.room.models.RoomVM;
import com.spurvago.server.room_type.RoomTypeRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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

    void mapToEntity(Room dest, RoomFM src) {
        dest.setRoomNumber(src.getRoomNumber());
        dest.setRoomType(roomTypeRepository.findById(src.getRoomTypeId()));
        dest.setStatus(src.getStatus());
    }

    Room mapToEntity(RoomFM src) {
        Room dest = new Room();
        dest.setRoomNumber(src.getRoomNumber());
        dest.setRoomType(roomTypeRepository.findById(src.getRoomTypeId()));
        dest.setStatus(src.getStatus());

        return dest;
    }

    List<RoomVM> mapToList(List<Room> srcList) {
        List<RoomVM> destList = new ArrayList<>();
        for (Room srcEntity : srcList) {
            destList.add(mapToVM(srcEntity));
        }

        return destList;
    }
}
