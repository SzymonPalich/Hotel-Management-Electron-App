package com.spurvago.server.maintenance_ticket.models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;

@Getter
@Setter
public class MaintenanceTicketFM {
    private long roomId;
    private long employeeId;
    private String name;
    private String description;
    private BigDecimal partsPrice;
    private String technicianReport;
    private Date finalizationDate;
}
