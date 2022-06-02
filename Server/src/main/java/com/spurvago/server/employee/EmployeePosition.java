package com.spurvago.server.employee;

public enum EmployeePosition {
    // TODO: Uzupełnić o pozostałe role
    MANAGER("ROLE_MANAGER");

    public final String stringValue;

    EmployeePosition(String stringValue) {
        this.stringValue = stringValue;
    }
}
