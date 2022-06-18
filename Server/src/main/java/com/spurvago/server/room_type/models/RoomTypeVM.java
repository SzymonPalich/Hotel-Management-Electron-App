package com.spurvago.server.room_type.models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 *  Model wyświetlania typów pokojów
 */
@Getter
@Setter
public class RoomTypeVM {
    private Long id;
    private String type;
    private BigDecimal price;

    public RoomTypeVM() {
    }
}
