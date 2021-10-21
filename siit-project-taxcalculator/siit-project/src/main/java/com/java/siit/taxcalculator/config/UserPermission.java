package com.java.siit.taxcalculator.config;

public enum UserPermission {
    USER_READ("loginEntity:read"),
    USER_WRITE("loginEntity:write"),
    CALCULUS_READ("pfa:read"),
    CALCULUS_WRITE("pfa:write");


    private String permissions;

    UserPermission(String permissions) {
        this.permissions = permissions;
    }

    public String getPermissions() {
        return permissions;
    }
}
