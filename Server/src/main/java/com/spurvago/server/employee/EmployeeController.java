package com.spurvago.server.employee;

import com.spurvago.components.ListPaginated;
import com.spurvago.components.Pager;
import com.spurvago.database.Employee;
import com.spurvago.server.employee.models.EmployeeFM;
import com.spurvago.server.employee.models.EmployeeSelect;
import com.spurvago.server.employee.models.EmployeeVM;
import com.spurvago.server.room.models.RoomSelect;
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

    //<editor-fold desc="find()">
    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeVM find(@PathVariable Long id) {
        return employeeService.find(id);
    }
    //</editor-fold>

    //<editor-fold desc="getList()">
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ListPaginated<EmployeeVM> getList(Pager pager, String search) {
        return employeeService.getList(pager, search);
    }
    //</editor-fold>

    //<editor-fold desc="create()">
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeVM create(@RequestBody EmployeeFM newEntity) {
        return employeeService.create(newEntity);
    }
    //</editor-fold>

    //<editor-fold desc="update()">
    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeVM update(@PathVariable Long id, @RequestBody EmployeeFM newEntity) {
        if (!Employee.Position.ACCEPTED_VALUES.contains(newEntity.getPosition()))
            throw new ResponseStatusException(UNPROCESSABLE_ENTITY);
        return employeeService.update(id, newEntity);
    }
    //</editor-fold>

    //<editor-fold desc="delete()">
    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        employeeService.delete(id);
    }
    //</editor-fold>

    //<editor-fold desc="getEmployeesByPosition">
    @RequestMapping(method = RequestMethod.GET, path = "/position")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getEmployeesByPosition(@RequestParam String position) {
        return employeeService.findByPosition(position);
    }
    //</editor-fold>

    //<editor-fold desc="getSelectList()">
    @GetMapping(path = "/select-list")
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeSelect> getSelectList() {
        return employeeService.getSelectList();
    }
    //</editor-fold>

    //<editor-fold desc="getSelectListPosition()">
    @RequestMapping(method = RequestMethod.GET, path = "/select-list/position")
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeSelect> getPositionSelectList(@RequestParam String position) {
        return employeeService.findByPositionSelect(position);
    }
    //</editor-fold>
}
