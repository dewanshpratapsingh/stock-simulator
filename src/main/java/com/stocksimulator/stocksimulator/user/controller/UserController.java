package com.example.stocksim.user.controller;

import com.example.stocksim.auth.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/profile")
    public User getProfile(Authentication authentication) {
        return (User) authentication.getPrincipal();
    }
}
