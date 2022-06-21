package com.spurvago.server.room.models;

import lombok.Getter;
import lombok.Setter;

/**
 * Model formularza pokojów
 */
@Getter
@Setter
public class RoomFM {
    private Long roomTypeId;
    private int roomNumber;
}
