package com.spurvago.server.employee;

public enum EmployeePosition {
    // TODO: Uzupełnić o pozostałe role
    MANAGER("ROLE_MANAGER"),
    MAID("ROLE_MAID"),
    TECHNICIAN("ROLE_TECHNICIAN"),
    RECEPTIONIST("ROLE_RECEPTIONIST");


    public final String stringValue;

    EmployeePosition(String stringValue) {
        this.stringValue = stringValue;
    }
}
