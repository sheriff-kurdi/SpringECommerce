package com.kurdi.security;

import lombok.Data;

@Data
public class UsernameAndPasswordAuthenticationRequest {
    private String username;
    private String password;
}
