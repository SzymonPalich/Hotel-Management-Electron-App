package com.spurvago.server.accommodation.models;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class AccommodationFM {
    private long RoomId;
    private long ClientId;
    private Date startDate;
    private Date endDate;
    private Boolean reservationOnly;
}
