package com.uas.dto;

import com.uas.enums.Role;

public class LoginResponse {

    private String token;
    private Role role;
    private String message;

    public LoginResponse(String token, Role role, String message) {
        this.token = token;
        this.role = role;
        this.message = message;
    }

    // getters and setters
    public String getToken() {
        return token;
    }

    public Role getRole() {
        return role;
    }

    public String getMessage() {
        return message;
    }
}


//	Spring Boot uses Jackson to convert Java objects to JSON.
//	Jackson needs public getters to access private fields.
//	Without getters, your JSON response will be empty: