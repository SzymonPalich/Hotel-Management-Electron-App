package com.spurvago.server.maid_ticket;

import com.spurvago.components.IMappableEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table
public class MaidTicket implements IMappableEntity<MaidTicket> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private long id;

    @Getter
    @Setter
    private long room_id;

    @Getter
    @Setter
    private long maid_id;

    @Getter
    @Setter
    private Date finalization_date;

    @Override
    public void map(MaidTicket source) {
        this.setRoom_id(source.room_id);
        this.setMaid_id(source.maid_id);
        this.setFinalization_date(source.finalization_date);
    }
}
