package com.spurvago.server.accommodation.models;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class AccommodationVM {
    private Long id;
    private String clientFirstName;
    private String clientLastName;
    private int roomNumber;
    private String roomType;
    private int roomStatus;
    private Date startDate;
    private Date endDate;
    private Boolean reservationOnly;

    public AccommodationVM() {
    }
}
