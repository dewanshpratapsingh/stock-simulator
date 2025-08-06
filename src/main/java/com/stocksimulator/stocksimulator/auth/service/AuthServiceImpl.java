package com.example.stocksim.auth.service;

import com.example.stocksim.auth.dto.AuthResponse;
import com.example.stocksim.auth.dto.LoginRequest;
import com.example.stocksim.auth.dto.RegisterRequest;
import com.example.stocksim.auth.model.User;
import com.example.stocksim.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public AuthResponse register(RegisterRequest request) {
        // Check if email already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        // Create new user
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(User.Role.TRADER); // Default role for registration

        userRepository.save(user);

        // Generate JWT
        String token = jwtService.generateToken(user.getEmail(), user.getRole().name());

        return new AuthResponse(token, user.getName(), user.getEmail(), user.getRole().name());
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        // Find user by email
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        // Validate password
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        // Generate JWT
        String token = jwtService.generateToken(user.getEmail(), user.getRole().name());

        return new AuthResponse(token, user.getName(), user.getEmail(), user.getRole().name());
    }
}
