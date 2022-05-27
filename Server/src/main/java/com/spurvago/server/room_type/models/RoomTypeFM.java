package com.spurvago.server.room_type.models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RoomTypeFM {
    private Long id;
    private String type;
    private BigDecimal price;
}
