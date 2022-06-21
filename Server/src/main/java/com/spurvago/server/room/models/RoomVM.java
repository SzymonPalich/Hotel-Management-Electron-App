package com.spurvago.server.room.models;

import lombok.Getter;
import lombok.Setter;

/**
 * Model wyświetlania pokojów
 */
@Getter
@Setter
public class RoomVM {
    private Long id;
    private int roomNumber;
    private Long roomTypeId;
    private String roomType;

    public RoomVM() {
    }
}
