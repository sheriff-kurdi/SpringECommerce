package com.kurdi.springecommerce.security;

public enum Roles {
    ADMIN("ROLE_ADMIN"),
    Editor("ROLE_EDITOR");



    private final String role;
    Roles(String role) {
        this.role = role;
    }

    public String getRole()
    {
        return this.role;
    }
}
