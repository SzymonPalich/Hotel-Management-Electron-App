package com.spurvago.server.room;

/**
 * Enumerator ze statusami pokoi
 */
public enum RoomStatus {
    FREE(1),
    FULL(2),
    ACCOMMODATION(3),
    CLEANING(4),
    REPAIR(5),
    BLOCKED(6);

    public final int intValue;

    RoomStatus(int intValue) {
        this.intValue = intValue;
    }
}
