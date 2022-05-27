package com.spurvago.server.maintenance_ticket.models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;

@Getter
@Setter
public class MaintenanceTicketVM {
    private Long id;
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
}
