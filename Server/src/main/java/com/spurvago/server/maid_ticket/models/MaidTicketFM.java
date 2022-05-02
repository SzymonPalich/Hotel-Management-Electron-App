package com.spurvago.server.maid_ticket.models;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class MaidTicketFM {
    private long roomId;
    private Date finalizationDate;
    private long employeeId;
}
