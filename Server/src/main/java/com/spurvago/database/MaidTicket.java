package com.spurvago.database;


import com.spurvago.server.maid_ticket.models.MaidTicketFM;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table
public class MaidTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @ManyToOne
    @Getter
    @Setter
    private Room room;

    @Getter
    @Setter
    private Date finalizationDate;

    @ManyToOne
    @Getter
    @Setter
    private Employee employee;

    public MaidTicket() {
    }
}
