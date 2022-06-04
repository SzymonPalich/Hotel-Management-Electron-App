package com.spurvago.server.room;

import com.spurvago.database.Room;
import com.spurvago.database.RoomType;
import com.spurvago.server.room.models.RoomFM;
import com.spurvago.server.room.models.RoomVM;
import com.spurvago.server.room_type.RoomTypeRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Component
public record RoomMapper(RoomTypeRepository roomTypeRepository) {
    RoomVM mapToVM(Room src) {
        RoomVM dest = new RoomVM();

        dest.setId(src.getId());
        dest.setRoomNumber(src.getRoomNumber());
        dest.setRoomType(src.getRoomType().getType());
        dest.setStatus(src.getStatus());
        dest.setRoomTypeId(src.getRoomType().getId());

        return dest;
    }

    void mapToEntity(Room dest, RoomFM src) {
        dest.setRoomNumber(src.getRoomNumber());
        Optional<RoomType> optionalRoomType = roomTypeRepository.findById(src.getRoomTypeId());
        RoomType entity;
        if (optionalRoomType.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND);
        }
        entity = optionalRoomType.get();
        dest.setRoomType(entity);
        dest.setStatus(src.getStatus());
    }

    Room mapToEntity(RoomFM src) {
        Room dest = new Room();
        dest.setRoomNumber(src.getRoomNumber());
        Optional<RoomType> optionalRoomType = roomTypeRepository.findById(src.getRoomTypeId());
        RoomType entity;
        if (optionalRoomType.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND);
        }
        entity = optionalRoomType.get();
        dest.setRoomType(entity);
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
