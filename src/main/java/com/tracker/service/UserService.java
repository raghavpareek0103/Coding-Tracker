package com.tracker.service;

import com.tracker.dto.LoginDto;
import com.tracker.dto.RegisterDto;

public interface UserService {
    String register(RegisterDto dto);
    String login(LoginDto dto);
}