package com.spurvago.server.maid_ticket.models;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class MaidTicketVM {
    private long id;
    private long roomId;
    private int roomNumber;
    private String roomType;
    private int roomStatus;
    private Date finalizationDate;
    private Long employeeId;
    private String employeeFirstName;
    private String employeeLastName;

    public MaidTicketVM() {
    }
}
