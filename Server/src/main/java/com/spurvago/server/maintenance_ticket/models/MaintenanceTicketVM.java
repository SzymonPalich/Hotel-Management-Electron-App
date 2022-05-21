package com.spurvago.server.maintenance_ticket.models;

import com.spurvago.database.MaintenanceTicket;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;

@Getter
@Setter
public class MaintenanceTicketVM {
    private long id;
    private int roomNumber;
    private String roomType;
    private int roomStatus;
    private String employeeFirstName;
    private String employeeLastName;
    private String name;
    private String description;
    private BigDecimal partsPrice;
    private String technicianReport;
    private Date finalizationDate;

    public MaintenanceTicketVM() {

    }

    public MaintenanceTicketVM(MaintenanceTicket entity) {
        this.id = entity.getId();
        this.roomNumber = entity.getRoom().getRoomNumber();
        this.roomNumber = entity.getRoom().getRoomNumber();
        this.roomType = entity.getRoom().getRoomType().getType();
        this.roomStatus = entity.getRoom().getStatus();
        if (!(entity.getEmployee() == null)) {
            this.employeeFirstName = entity.getEmployee().getFirstName();
            this.employeeLastName = entity.getEmployee().getLastName();
        } else {
            this.employeeFirstName = "";
            this.employeeLastName = "";
        }
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.partsPrice = entity.getPartsPrice();
        this.technicianReport = entity.getTechnicianReport();
        this.finalizationDate = entity.getFinalizationDate();
    }
}
