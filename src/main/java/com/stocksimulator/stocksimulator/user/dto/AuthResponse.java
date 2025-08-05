package com.example.stocksim.auth.dto;

public class AuthResponse {

    private String token;
    private String name;
    private String email;
    private String role;

    public AuthResponse(String token, String name, String email, String role) {
        this.token = token;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    // Getters only (response objects are usually immutable)
    public String getToken() {
        return token;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }
}
