package com.spurvago.server.employee;

import com.spurvago.components.IMappableEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Entity
@Table
public class Employee implements IMappableEntity<Employee> {
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
    private String password;

    @Getter
    @Setter
    private String phoneNumber;

    @Getter
    @Setter
    private String position;

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

    @Override
    public void map(Employee source) {
        this.setName(source.getName());
        this.setSurname(source.getSurname());
        this.setEmail(source.getEmail());
        this.setPassword(source.getPassword());
        this.setPhoneNumber(source.getPhoneNumber());
        this.setPosition(source.getPosition());
        this.setPesel(source.getPesel());
        this.setEmploymentDate(source.getEmploymentDate());
        this.setDismissalDate(source.getDismissalDate());
        this.setSalary(source.getSalary());
    }

    public static final class Position {
        public final static List<String> ACCEPTED_VALUES = List.of("ROLE_MANAGER", "ROLE_MAID", "ROLE_TECHNICIAN", "ROLE_RECEPTIONIST");
    }
}
