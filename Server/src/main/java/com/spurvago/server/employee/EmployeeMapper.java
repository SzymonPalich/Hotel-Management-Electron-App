package com.spurvago.server.employee;

import com.spurvago.database.Employee;
import com.spurvago.server.employee.models.EmployeeFM;
import com.spurvago.server.employee.models.EmployeeSelect;
import com.spurvago.server.employee.models.EmployeeVM;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public record EmployeeMapper() {
    EmployeeVM mapToVM(Employee src) {
        EmployeeVM dest = new EmployeeVM();
        dest.setId(src.getId());
        dest.setFirstName(src.getFirstName());
        dest.setLastName(src.getLastName());
        dest.setEmail(src.getEmail());
        dest.setPhoneNumber(src.getPhoneNumber());
        dest.setPosition(src.getPosition());
        dest.setPesel(src.getPesel());
        dest.setEmploymentDate(src.getEmploymentDate());
        dest.setDismissalDate(src.getDismissalDate());
        dest.setSalary(src.getSalary());

        return dest;
    }

    void mapToEntity(Employee dest, EmployeeFM src) {
        dest.setFirstName(src.getFirstName());
        dest.setLastName(src.getLastName());
        dest.setPassword(src.getPassword());
        dest.setEmail(src.getEmail());
        dest.setPhoneNumber(src.getPhoneNumber());
        dest.setPosition(src.getPosition());
        dest.setPesel(src.getPesel());
        dest.setEmploymentDate(src.getEmploymentDate());
        dest.setDismissalDate(src.getDismissalDate());
        dest.setSalary(src.getSalary());
    }

    Employee mapToEntity(EmployeeFM src) {
        Employee dest = new Employee();
        dest.setFirstName(src.getFirstName());
        dest.setLastName(src.getLastName());
        dest.setPassword(src.getPassword());
        dest.setEmail(src.getEmail());
        dest.setPhoneNumber(src.getPhoneNumber());
        dest.setPosition(src.getPosition());
        dest.setPesel(src.getPesel());
        dest.setEmploymentDate(src.getEmploymentDate());
        dest.setDismissalDate(src.getDismissalDate());
        dest.setSalary(src.getSalary());

        return dest;
    }

    List<EmployeeVM> mapToList(List<Employee> srcList) {
        List<EmployeeVM> destList = new ArrayList<>();
        for (Employee srcEntity : srcList) {
            destList.add(mapToVM(srcEntity));
        }

        return destList;
    }

    EmployeeSelect mapToSelect(Employee src) {
        EmployeeSelect dest = new EmployeeSelect();
        dest.setId(src.getId());
        dest.setLabel(src.getFirstName() + " " + src.getLastName());

        return dest;
    }

    List<EmployeeSelect> mapToSelectList(List<Employee> srcList) {
        List<EmployeeSelect> destList = new ArrayList<>();
        for (Employee src : srcList) {
            destList.add(mapToSelect(src));
        }

        return destList;
    }
}
