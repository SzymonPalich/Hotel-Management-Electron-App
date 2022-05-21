package com.spurvago.server.accommodation.models;

import com.spurvago.database.Accommodation;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class AccommodationVM {
    private long id;
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
