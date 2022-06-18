package com.spurvago.server.maintenance_ticket.models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * Model formularza zgłoszeń naprawy
 */
@Getter
@Setter
public class MaintenanceTicketFM {
    private Long roomId;
    private Long employeeId;
    private String name;
    private String description;
    private BigDecimal partsPrice;
    private String technicianReport;
    private Date finalizationDate;
}
