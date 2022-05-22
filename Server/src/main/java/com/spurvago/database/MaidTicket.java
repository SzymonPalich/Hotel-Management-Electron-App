package com.spurvago.database;


import com.spurvago.database.Employee;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table
public class MaidTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @ManyToOne
    @Getter
    @Setter
    private Room room;

    @Getter
    @Setter
    private Date finalizationDate;

    @ManyToOne
    @Getter
    @Setter
    private Employee employee;

    public MaidTicket() {
    }
}
