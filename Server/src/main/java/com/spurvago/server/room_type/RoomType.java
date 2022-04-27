package com.spurvago.server.room_type;

import com.spurvago.components.IBaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table
public class RoomType implements IBaseEntity<RoomType> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    @Setter
    private String Type;

    @Getter
    @Setter
    private BigDecimal Price;


    @Override
    public void map(RoomType source) {
        this.setPrice(source.getPrice());
        this.setType(source.getType());
    }

    @Override
    public boolean validate() {
        return true;
    }
}
