package com.spurvago.server.employee;

import com.spurvago.components.IBaseService;
import com.spurvago.components.ListPaginated;
import com.spurvago.components.Pager;
import com.spurvago.components.Utils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Service
public record EmployeeService(EmployeeRepository employeeRepository) implements IBaseService<Employee> {


    @Override
    public Employee find(long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public ListPaginated<Employee> getList(Pager pager) {
        Pageable pageable = pager.makePageable();
        Page<Employee> entities = employeeRepository.findAll(pageable);
        ListPaginated<Employee> listPaginated = new ListPaginated<>(entities, pager);
        return listPaginated;
    }

    @Override
    public Employee create(Employee newEntity) {
        if (!Utils.validateEmail(newEntity.getEmail())) {
            throw new ResponseStatusException(UNPROCESSABLE_ENTITY);
        }
        return employeeRepository.save(newEntity);
    }

    @Override
    public Employee update(Employee oldEntity, Employee newEntity) {
        oldEntity.map(newEntity);

        return employeeRepository.save(oldEntity);
    }

    @Override
    public void delete(Employee Entity) {
        employeeRepository.delete(Entity);
    }
}
