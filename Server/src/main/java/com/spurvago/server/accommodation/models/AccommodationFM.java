package com.spurvago.server.accommodation.models;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class AccommodationFM {
    private long id;
    private long clientId;
    private long roomId;
    private Date startDate;
    private Date endDate;
    private Boolean reservationOnly;
}
