package com.tracker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.tracker.dto.LoginDto;
import com.tracker.dto.RegisterDto;
import com.tracker.entity.User;
import com.tracker.repository.UserRepository;
import com.tracker.security.JwtUtil;
import com.tracker.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired UserRepository userRepo;
    @Autowired BCryptPasswordEncoder encoder;
    @Autowired JwtUtil jwtUtil;

    @Override
    public String register(RegisterDto dto) {
        if (userRepo.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(encoder.encode(dto.getPassword()));
        userRepo.save(user);
        return "REGISTERED";
    }

    @Override
    public String login(LoginDto dto) {
        User user = userRepo.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!encoder.matches(dto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return jwtUtil.generateToken(dto.getEmail());
    }
}