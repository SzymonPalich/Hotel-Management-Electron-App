package com.spurvago.server.maid_ticket.models;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

/**
 * Model formularza zgłoszeń sprzątania
 */
@Getter
@Setter
public class MaidTicketFM {
    private Long employeeId;
    private Long roomId;
    private Date finalizationDate;
}
