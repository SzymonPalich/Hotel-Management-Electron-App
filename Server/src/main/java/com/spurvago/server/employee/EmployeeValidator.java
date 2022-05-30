package com.spurvago.server.employee;

import com.spurvago.components.Validator;
import com.spurvago.server.employee.models.EmployeeFM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class EmployeeValidator extends Validator {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeValidator(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public boolean validate(EmployeeFM model) {
        if (!(haveLength(model.getFirstName(), 50))) {
            return false;
        }
        if (!(haveLength(model.getLastName(),50))) {
            return false;
        }
        if (!(haveLength(model.getEmail(), 50)) && !isEmail(model.getEmail())) {
            return false;
        }
        if (!(haveLength(model.getPhoneNumber(),9, 9))) {
            return false;
        }
        if (!(model.getPosition() > 0) && !(model.getPosition() < 5)) {
            return false;
        }
        if (!(haveLength(model.getPesel(), 11, 11))) {
            return false;
        }
        if (!model.getEmploymentDate().before(new Date())) {
            return false;
        }
        if(model.getDismissalDate() != null) {
            if (model.getDismissalDate().before(model.getEmploymentDate())) {
                return false;
            }
        }
        return correctDecimal(model.getSalary(), 8, 2);
    }
}
