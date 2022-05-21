package com.spurvago.server.employee;

import com.spurvago.database.MaidTicket;
import com.spurvago.database.MaintenanceTicket;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Entity
@Table
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    @Setter
    private String firstName;

    @Getter
    @Setter
    private String lastName;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private String phoneNumber;

    @Getter
    @Setter
    private int position;

    @Getter
    @Setter
    private String pesel;

    @Getter
    @Setter
    private Date employmentDate;

    @Getter
    @Setter
    private Date dismissalDate;

    @Getter
    @Setter
    private BigDecimal salary;

    @OneToMany
    private List<MaidTicket> maidTickets;

    @OneToMany
    private List<MaintenanceTicket> maintenanceTickets;


    public static final class Position {
        public final static List<String> ACCEPTED_VALUES = List.of("ROLE_MANAGER", "ROLE_MAID", "ROLE_TECHNICIAN", "ROLE_RECEPTIONIST");
    }
}
