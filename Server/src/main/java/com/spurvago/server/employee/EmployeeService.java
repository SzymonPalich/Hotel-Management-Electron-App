package com.spurvago.server.employee;

import com.spurvago.components.ListPaginated;
import com.spurvago.components.Pager;
import com.spurvago.components.Utils;
import com.spurvago.database.Employee;
import com.spurvago.server.client.models.ClientVM;
import com.spurvago.server.employee.models.EmployeeFM;
import com.spurvago.server.employee.models.EmployeeVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Service
public record EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {

    public EmployeeVM find(long id) {
        Employee entity = employeeRepository.findById(id);
        if (entity == null) {
            throw new ResponseStatusException(NOT_FOUND);
        }

        return employeeMapper.mapToVM(entity);
    }

    public ListPaginated<EmployeeVM> getList(Pager pager, String search) {
        Pageable pageable = pager.makePageable();
        Page<Employee> entities;

        if (Utils.isNullOrBlank(search)) {
            entities = employeeRepository.findAll(pageable);
        } else {
            List<String> words = List.of(search.split("\\s"));
            Specification<Employee> specification = EmployeeRepository.search(words);
            entities = employeeRepository.findAll(specification, pageable);
        }

        List<EmployeeVM> entitiesDTO = employeeMapper.mapToList(entities.getContent());
        return new ListPaginated<>(entitiesDTO, pager,
                entities.getTotalElements(), entities.getTotalPages());
    }


    public EmployeeVM create(EmployeeFM newEntity) {
        // TODO Dodać validator
        Employee entity = employeeMapper.mapToEntity(newEntity);
        // TODO Email sprawdzać w validatorze
        if (!Utils.validateEmail(newEntity.getEmail())) {
            throw new ResponseStatusException(UNPROCESSABLE_ENTITY);
        }
        employeeRepository.save(entity);
        return employeeMapper.mapToVM(entity);
    }


    public EmployeeVM update(long id, EmployeeFM newEntity) {
        // TODO Dodać validator
        Employee entity = employeeRepository.findById(id);
        if (entity == null) {
            throw new ResponseStatusException(NOT_FOUND);
        }

        employeeMapper.mapToEntity(entity, newEntity);
        employeeRepository.save(entity);
        return employeeMapper.mapToVM(entity);
    }

    public void delete(long id) {
        Employee entity = employeeRepository.findById(id);
        if (entity == null) {
            throw new ResponseStatusException(NOT_FOUND);
        }

        employeeRepository.delete(entity);
    }

    // TODO Tak jak w kontrolerze pisałem
    public List<Employee> findByPosition(int position) {
        return employeeRepository.findEmployeesByPosition(position);
    }
}
