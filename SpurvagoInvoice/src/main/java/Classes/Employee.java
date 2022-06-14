package Classes;

import java.math.BigDecimal;
import java.sql.Date;

public class Employee {
    private String  firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private int position;
    private String pesel;
    private Date employmentDate;
    private Date dismissalDate;
    private BigDecimal salary;

    public Employee(String firstName, String lastName, String email, String phoneNumber, int position, String pesel, Date employmentDate, Date dismissalDate, BigDecimal salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.position = position;
        this.pesel = pesel;
        this.employmentDate = employmentDate;
        this.dismissalDate = dismissalDate;
        this.salary = salary;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public void setEmploymentDate(Date employmentDate) {
        this.employmentDate = employmentDate;
    }

    public void setDismissalDate(Date dismissalDate) {
        this.dismissalDate = dismissalDate;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getPosition() {
        return position;
    }

    public String getPesel() {
        return pesel;
    }

    public Date getEmploymentDate() {
        return employmentDate;
    }

    public Date getDismissalDate() {
        return dismissalDate;
    }

    public BigDecimal getSalary() {
        return salary;
    }
}