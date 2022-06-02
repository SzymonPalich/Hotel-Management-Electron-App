package com.spurvago.server.employee;

public enum EmployeePosition {
    MANAGER("ROLE_MANAGER");

    public final String stringValue;

    EmployeePosition(String stringValue) {
        this.stringValue = stringValue;
    }
}
