package com.spurvago.server.employee.models;

import lombok.Getter;
import lombok.Setter;

/**
 * Model wyświetlania pracowników w listach rozwijanych
 */
@Getter
@Setter
public class EmployeeSelect {
    private Long id;
    private String label;
}
