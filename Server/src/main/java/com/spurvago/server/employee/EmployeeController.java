package com.spurvago.server.employee;

import com.spurvago.components.IBaseController;
import com.spurvago.components.ListPaginated;
import com.spurvago.components.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@RestController
@RequestMapping(path="/api/employee")
public class EmployeeController implements IBaseController<Employee> {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee find(Long id) {
        Employee entity = employeeService.find(id);
        if (entity == null) {
            throw new ResponseStatusException(NOT_FOUND);
        }

        return entity;
    }

    @Override
    public ListPaginated<Employee> getList(Pager pager, String search) {
        ListPaginated<Employee> entities = employeeService.getList(pager, search);

        return entities;
    }

    @Override
    public Employee create(Employee newEntity) {
        if (!Employee.Position.ACCEPTED_VALUES.contains(newEntity.getPosition()))
            throw new ResponseStatusException(UNPROCESSABLE_ENTITY);

        Employee entity = employeeService.create(newEntity);

        return entity;
    }

    @Override
    public Employee update(Long id, Employee newEntity) {
        if (!Employee.Position.ACCEPTED_VALUES.contains(newEntity.getPosition()))
            throw new ResponseStatusException(UNPROCESSABLE_ENTITY);

        Employee entity = employeeService.find(id);
        if (entity == null)
            throw new ResponseStatusException(NOT_FOUND);

        entity = employeeService.update(entity, newEntity);

        return entity;
    }

    @Override
    public void delete(Long id) {
        Employee entity = employeeService.find(id);
        if (entity == null) {
            throw new ResponseStatusException(NOT_FOUND);
        }

        employeeService.delete(entity);
    }

    @GetMapping(path = "/name")
    public ListPaginated<Employee> getFiltered(@RequestParam String input, Pager pager) {
        return employeeService.getFiltered(input, pager);
    }
}
