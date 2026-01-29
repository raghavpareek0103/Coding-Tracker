package com.tracker.controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.tracker.repository.UserRepository;
import com.tracker.entity.User;
import com.tracker.dto.LoginDto;

@RestController
@RequestMapping("/auth")
public class AuthController {

 @Autowired UserRepository userRepo;
 @Autowired BCryptPasswordEncoder encoder;

 @PostMapping("/register")
 public String register(@RequestBody User user) {
  user.setPassword(encoder.encode(user.getPassword()));
  userRepo.save(user);
  return "REGISTERED";
 }

 @PostMapping("/login")
 public String login(@RequestBody LoginDto dto) {
  User user = userRepo.findByEmail(dto.getEmail()).orElseThrow();
  return encoder.matches(dto.getPassword(), user.getPassword())
    ? "LOGIN_SUCCESS" : "INVALID";
 }
}
