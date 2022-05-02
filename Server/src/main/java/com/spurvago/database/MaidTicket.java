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
    private long id;

    @Getter
    @Setter
    private long roomId;

    @Getter
    @Setter
    private Date finalizationDate;

    @ManyToOne
    @Getter
    @Setter
    private Employee employee;

    public MaidTicket() {
    }

    public void map(MaidTicketFM formModel) {
        roomId = formModel.getRoomId();
        finalizationDate = formModel.getFinalizationDate();
    }
}
