package com.spurvago.server.employee;

/**
 * Role pracowników
 */
public enum EmployeePosition {
    MANAGER("ROLE_MANAGER"),
    MAID("ROLE_MAID"),
    TECHNICIAN("ROLE_TECHNICIAN"),
    RECEPTIONIST("ROLE_RECEPTIONIST");


    public final String stringValue;

    EmployeePosition(String stringValue) {
        this.stringValue = stringValue;
    }
}
