package com.spurvago.server.employee;

import com.spurvago.components.ListPaginated;
import com.spurvago.components.Pager;
import com.spurvago.database.Employee;
import com.spurvago.server.employee.models.EmployeeFM;
import com.spurvago.server.employee.models.EmployeeVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeVM find(@PathVariable long id) {
        return employeeService.find(id);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ListPaginated<EmployeeVM> getList(Pager pager, String search) {
        return employeeService.getList(pager, search);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeVM create(EmployeeFM newEntity) {
        return employeeService.create(newEntity);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeVM update(@PathVariable Long id, @RequestBody EmployeeFM newEntity) {
        if (!Employee.Position.ACCEPTED_VALUES.contains(newEntity.getPosition()))
            throw new ResponseStatusException(UNPROCESSABLE_ENTITY);
        return employeeService.update(id, newEntity);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        employeeService.delete(id);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, path = "/position")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getEmployeesByPosition(@RequestParam int position) {
        return employeeService.findByPosition(position);
    }
}
