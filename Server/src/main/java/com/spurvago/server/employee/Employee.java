package com.spurvago.server.employee;

import com.spurvago.components.IBaseEntity;
import com.spurvago.server.maid_ticket.MaidTicket;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Employee implements IBaseEntity<Employee> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String surname;

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

    @OneToMany(mappedBy = "employee")
    private List<MaidTicket> maidTickets = new ArrayList<>();

    @Override
    public void map(Employee source) {
        this.setName(source.getName());
        this.setSurname(source.getSurname());
        this.setEmail(source.getEmail());
        this.setPhoneNumber(source.getPhoneNumber());
        this.setPosition(source.getPosition());
        this.setPesel(source.getPesel());
        this.setEmploymentDate(source.getEmploymentDate());
        this.setDismissalDate(source.getDismissalDate());
        this.setSalary(source.getSalary());
    }

    @Override
    public boolean validate() {
        return true;
    }

    public static final class Position {
        public final static List<Integer> ACCEPTED_VALUES = List.of(1, 2, 3, 4);
        public final static int MANAGER = 1;
        public final static int MAID = 2;
        public final static int TECHNICIAN = 3;
        public final static int RECEPTIONIST = 4;
    }
}
