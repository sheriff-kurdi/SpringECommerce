package com.kurdi.springecommerce.security;

public enum Permissions {
    EmployeeRead("product:read"),
    EmployeeWrite("product:write");


    private final String permission;
    Permissions(String permission) {
        this.permission = permission;
    }
    public String getPermission()
    {
        return this.permission;
    }
}
