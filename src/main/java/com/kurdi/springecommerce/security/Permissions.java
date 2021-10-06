package com.kurdi.springecommerce.security;

public enum Permissions {
    EmployeeRead("employee:read"),
    EmployeeWrite("employee:write");


    private final String permission;
    Permissions(String permission) {
        this.permission = permission;
    }
    public String getPermission()
    {
        return this.permission;
    }
}
