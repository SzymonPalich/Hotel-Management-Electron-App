package com.spurvago.database;

import com.spurvago.database.Employee;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table
public class MaintenanceTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @ManyToOne
    @Getter
    @Setter
    private Room room;

    @ManyToOne
    @Getter
    @Setter
    private Employee employee;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private BigDecimal partsPrice;

    @Getter
    @Setter
    private String technicianReport;

    @Getter
    @Setter
    private Date finalizationDate;
}
