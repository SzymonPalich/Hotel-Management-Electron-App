package com.spurvago.database;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * Typy pokoi
 */
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
    @JoinTable(name = "room")
    private List<Room> rooms;
}
