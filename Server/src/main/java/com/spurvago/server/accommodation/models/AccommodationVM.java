package com.spurvago.server.accommodation.models;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class AccommodationVM {
    private Long id;
    private Long clientId;
    private String clientFirstName;
    private String clientLastName;
    private Long roomId;
    private int roomNumber;
    private String roomType;
    private int roomStatus;
    private Date startDate;
    private Date endDate;

    public AccommodationVM() {
    }
}
