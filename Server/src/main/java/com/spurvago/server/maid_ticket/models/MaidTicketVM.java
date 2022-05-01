package com.spurvago.server.maid_ticket.models;

import com.spurvago.database.MaidTicket;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class MaidTicketVM {
    private long id;
    private long roomId;
    private Date finalizationDate;
    private long employeeId;
    private String employeeFirstName;
    private String employeeLastName;

    public MaidTicketVM() {
    }

    public MaidTicketVM(MaidTicket entity) {
        this.id = entity.getId();
        this.roomId = entity.getRoomId();
        this.finalizationDate = entity.getFinalizationDate();
        this.employeeId = entity.getEmployee().getId();
        this.employeeFirstName = entity.getEmployee().getFirstName();
        this.employeeLastName = entity.getEmployee().getLastName();
    }
}
