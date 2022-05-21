package com.spurvago.server.room_type;

import com.spurvago.database.RoomType;
import com.spurvago.server.room_type.models.RoomTypeFM;
import com.spurvago.server.room_type.models.RoomTypeVM;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public record RoomTypeMapper() {
    RoomTypeVM mapToVM(RoomType src) {
        RoomTypeVM dest = new RoomTypeVM();

        dest.setId(src.getId());
        dest.setType(src.getType());
        dest.setPrice(src.getPrice());

        return dest;
    }

    RoomType mapToEntity(RoomTypeFM src) {
        RoomType dest = new RoomType();
        dest.setType(src.getType());
        dest.setPrice(src.getPrice());
        return dest;
    }

    void mapToEntity(RoomType dest, RoomTypeFM src) {
        dest.setType(src.getType());
        dest.setPrice(src.getPrice());
    }

    List<RoomTypeVM> mapToList(List<RoomType> srcList) {
        List<RoomTypeVM> destList = new ArrayList<>();
        for (RoomType srcEntity : srcList) {
            destList.add(mapToVM(srcEntity));
        }

        return destList;
    }
}
