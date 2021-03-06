package com.spurvago.server.employee.models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * Model wyświetlania pracowników
 */
@Getter
@Setter
public class EmployeeVM {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String position;
    private String pesel;
    private Date employmentDate;
    private Date dismissalDate;
    private BigDecimal salary;

    public EmployeeVM() {
    }
}
