package com.spurvago.server.room.models;

import com.spurvago.database.Room;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomVM {
    private long id;
    private int roomNumber;
    private String roomType;
    private int status;

    public RoomVM() {
    }

    public RoomVM(Room entity) {
        this.id = entity.getId();
        this.roomNumber = entity.getRoomNumber();
        this.roomType = entity.getRoomType().getType();
        this.status = entity.getStatus();
    }
}
