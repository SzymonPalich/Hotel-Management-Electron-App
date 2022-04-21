package com.spurvago.server.employee;

import com.spurvago.components.IMappableEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

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


    @Override
    public void map(Employee source) {
        this.setName(source.name);
        this.setSurname(source.surname);
        this.setEmail(source.email);
        this.setPhoneNumber(source.phoneNumber);
        this.setPosition(source.position);
        this.setPesel(source.pesel);
        this.setEmploymentDate(source.employmentDate);
        this.setDismissalDate(source.dismissalDate);
        this.setSalary(source.salary);
    }
}
