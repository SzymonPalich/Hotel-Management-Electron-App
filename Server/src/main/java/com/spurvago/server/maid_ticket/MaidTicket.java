package com.spurvago.server.maid_ticket;


import com.spurvago.components.IBaseEntity;
import com.spurvago.server.employee.Employee;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table
public class MaidTicket implements IBaseEntity<MaidTicket> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private long id;

    @Getter
    @Setter
    private long room_id;

    @Getter
    @Setter
    private long employee_id;

    @ManyToOne
    private Employee employee;

    @Getter
    @Setter
    private Date finalization_date;

    @Override
    public void map(MaidTicket source) {
        this.setRoom_id(source.room_id);
        // this.setEmployee_id(source.employee_id);
        this.setFinalization_date(source.finalization_date);
    }

    @Override
    public boolean validate() {
        return false;
    }
}
