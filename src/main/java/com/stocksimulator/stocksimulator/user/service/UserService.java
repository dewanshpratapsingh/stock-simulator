package com.example.stocksim.auth.service;

import com.example.stocksim.auth.dto.LoginRequest;
import com.example.stocksim.auth.dto.RegisterRequest;
import com.example.stocksim.auth.dto.AuthResponse;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}
