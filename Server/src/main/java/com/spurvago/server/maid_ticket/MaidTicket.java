package com.spurvago.server.maid_ticket;


import com.spurvago.components.IBaseEntity;
import com.spurvago.server.employee.Employee;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "maid_ticket")
public class MaidTicket implements IBaseEntity<MaidTicket> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private long id;

    @Getter
    @Setter
    private long room_id;

    @ManyToOne
    @JoinColumn(name = "maid_id")
    private Employee employee;

    @Getter
    @Setter
    private Date finalization_date;

    @Override
    public void map(MaidTicket source) {
        this.setRoom_id(source.room_id);
//        this.setMaid_id(source.maid_id);
        this.setFinalization_date(source.finalization_date);
    }

    @Override
    public boolean validate() {
        return false;
    }
}
