package com.spurvago.server.security;

import com.spurvago.database.Employee;
import com.spurvago.server.employee.EmployeeRepository;
import com.spurvago.server.security.models.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public record UserManager(EmployeeRepository employeeRepository) {

    public User getUser() {
        Employee employee = employeeRepository.findByEmail(getEmail());
        return new User(employee.getId(), employee.getFirstName(), employee.getLastName(),
                employee.getEmail(), employee.getPosition());
    }

    public long getId() {
        return getUser().getId();
    }

    public Employee getEmployee() {
        return employeeRepository.findByEmail(getEmail());
    }

    public String getRole() {
        Authentication auth = getAuth();
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        return authorities.toArray()[0].toString();
    }

    private Authentication getAuth() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    private String getEmail() {
        return getAuth().getPrincipal().toString();
    }
}
