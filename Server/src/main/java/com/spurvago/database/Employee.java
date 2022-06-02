package com.spurvago.database;

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
    @JoinTable(name = "maid_ticket")
    private List<MaidTicket> maidTickets;

    @OneToMany
    @JoinTable(name = "maintenance_ticket")
    private List<MaintenanceTicket> maintenanceTickets;

    // TODO Po merge autoryzacji do wywalenia
    public static final class Position {
        public final static List<Integer> ACCEPTED_VALUES = List.of(1, 2, 3, 4);
        public final static int MANAGER = 1;
        public final static int MAID = 2;
        public final static int TECHNICIAN = 3;
        public final static int RECEPTIONIST = 4;
    }
}
