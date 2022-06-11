package com.spurvago.database;

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
    @JoinTable(name = "maid_ticket")
    @Getter
    @Setter
    private List<MaidTicket> maidTickets;

    @OneToMany
    @JoinTable(name = "maintenance_ticket")
    @Getter
    @Setter
    private List<MaintenanceTicket> maintenanceTickets;

    @OneToMany
    @JoinTable(name = "accommodation")
    @Getter
    @Setter
    private List<Accommodation> accommodationList;
}