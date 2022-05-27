package com.spurvago.server.accommodation.models;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class AccommodationFM {
    private Long RoomId;
    private Long ClientId;
    private Date startDate;
    private Date endDate;
    private Boolean reservationOnly;
}
