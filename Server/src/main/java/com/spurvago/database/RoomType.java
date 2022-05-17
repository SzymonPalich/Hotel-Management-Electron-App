package com.spurvago.database;

import com.spurvago.components.IBaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table
public class RoomType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    @Setter
    private String type;

    @Getter
    @Setter
    private BigDecimal price;

    @OneToMany
    private List<Room> rooms;
}
