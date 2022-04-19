package com.spring.company.model;

public enum EmployeeRole {
    MEMBER(Constants.MEMBER),
    LEAD(Constants.LEAD),
    ADMIN(Constants.ADMIN);

    private String value;

    EmployeeRole(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static class Constants {
        public static final String ADMIN = "ROLE_ADMIN";
        public static final String MEMBER = "ROLE_MEMBER";
        public static final String LEAD = "ROLE_LEAD";
    }
}
