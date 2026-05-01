package com.tracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.tracker.dto.LoginDto;
import com.tracker.dto.RegisterDto;
import com.tracker.service.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired UserService userService; // interface, not impl

    @PostMapping("/register")
    public String register(@Valid @RequestBody RegisterDto dto) {
        return userService.register(dto);
    }

    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginDto dto) {
        return userService.login(dto);
    }
}