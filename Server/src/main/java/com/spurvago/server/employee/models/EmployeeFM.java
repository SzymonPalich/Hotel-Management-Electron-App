package com.spurvago.server.employee.models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;

@Getter
@Setter
public class EmployeeFM {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private int position;
    private String pesel;
    private Date employmentDate;
    private Date dismissalDate;
    private BigDecimal salary;


}
