package com.spurvago.database;

import com.spurvago.components.IBaseEntity;
import com.spurvago.components.Utils;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    @Setter
    private Integer roomNumber;

    @ManyToOne
    @Getter
    @Setter
    private RoomType roomType;

    @Getter
    @Setter
    private Integer status;

    @OneToMany
    private List<MaidTicket> maidTickets;

    @OneToMany
    private List<MaintenanceTicket> maintenanceTickets;

    @OneToMany
    private List<Accommodation> accommodationList;
}