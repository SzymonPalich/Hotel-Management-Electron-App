package com.spurvago.server.maid_ticket.models;

import com.spurvago.database.Employee;
import lombok.Getter;
import lombok.Setter;
import springfox.documentation.annotations.ApiIgnore;

import java.sql.Date;

@Getter
@Setter
public class MaidTicketFM {
    private long id;
    private long roomId;
    private Date finalizationDate;
    private long employeeId;
}
