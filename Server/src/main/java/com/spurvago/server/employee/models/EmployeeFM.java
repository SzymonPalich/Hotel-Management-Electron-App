package com.spurvago.server.employee.models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * Model formularza pracowników
 */
@Getter
@Setter
public class EmployeeFM {
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String phoneNumber;
    private String position;
    private String pesel;
    private Date employmentDate;
    private Date dismissalDate;
    private BigDecimal salary;
}
