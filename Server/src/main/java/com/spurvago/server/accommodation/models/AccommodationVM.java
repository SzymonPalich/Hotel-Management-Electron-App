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

    public AccommodationVM(Accommodation entity) {
        this.id = entity.getId();
        this.clientFirstName = entity.getClient().getFirstName();
        this.clientLastName = entity.getClient().getLastName();
        this.roomNumber = entity.getRoom().getRoomNumber();
        this.roomType = entity.getRoom().getRoomType().getType();
        this.roomStatus = entity.getRoom().getStatus();
        this.startDate = entity.getStartDate();
        this.endDate = entity.getEndDate();
        this.reservationOnly = entity.getReservationOnly();
    }
}
