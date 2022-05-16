package com.spurvago.server.maid_ticket.models;

import com.spurvago.database.MaidTicket;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.Objects;
import java.util.stream.Stream;

@Getter
@Setter
public class MaidTicketVM {
    private long id;
    private long roomId;
    private int roomNumber;
    private String roomType;
    private int roomStatus;
    private Date finalizationDate;
    private long employeeId;
    private String employeeFirstName;
    private String employeeLastName;

    public MaidTicketVM() {
    }

    public MaidTicketVM(MaidTicket entity) {
        this.id = entity.getId();
        this.roomId = entity.getRoom().getId();
        this.roomNumber = entity.getRoom().getRoomNumber();
        this.roomType = entity.getRoom().getRoomType().getType();
        this.roomStatus = entity.getRoom().getStatus();
        this.finalizationDate = entity.getFinalizationDate();
        if (!(entity.getEmployee() == null)) {
            this.employeeId = entity.getEmployee().getId();
            this.employeeFirstName = entity.getEmployee().getFirstName();
            this.employeeLastName = entity.getEmployee().getLastName();
        } else {
            this.employeeId = 0;
            this.employeeFirstName = "";
            this.employeeLastName = "";
        }
    }
}
