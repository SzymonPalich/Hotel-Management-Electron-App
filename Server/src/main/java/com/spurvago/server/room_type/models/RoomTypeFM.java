package com.spurvago.server.room_type.models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Model formularza typów pokojów
 */
@Getter
@Setter
public class RoomTypeFM {
    private Long id;
    private String type;
    private BigDecimal price;
}
