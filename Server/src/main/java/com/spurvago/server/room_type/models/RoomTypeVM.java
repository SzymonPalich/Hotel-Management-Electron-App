package com.spurvago.server.room_type.models;

import com.spurvago.database.RoomType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RoomTypeVM {
    private Long id;
    private String type;
    private BigDecimal price;

    public RoomTypeVM(){
    }

    public RoomTypeVM(RoomType entity) {
        this.id = entity.getId();
        this.type = entity.getType();
        this.price = entity.getPrice();
    }
}
