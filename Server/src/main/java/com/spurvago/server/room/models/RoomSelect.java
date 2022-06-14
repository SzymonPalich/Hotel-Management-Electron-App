package com.spurvago.server.room.models;

import lombok.Getter;
import lombok.Setter;

/**
 * Model wyświetlania pokojów w listach rozwijanych
 */
@Getter
@Setter
public class RoomSelect {
    private Long id;
    private String roomLabel;
}