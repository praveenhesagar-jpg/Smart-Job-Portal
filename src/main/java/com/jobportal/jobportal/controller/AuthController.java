package com.jobportal.jobportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.jobportal.jobportal.entity.User;
import com.jobportal.jobportal.service.UserService;
import com.jobportal.jobportal.util.JwtUtil;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")   // ✅ allow frontend requests
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    // ✅ LOGIN
    @PostMapping("/login")
    public String login(@RequestBody User user) {

        // 🔥 FIX: use service login method (important)
        User dbUser = userService.login(user.getEmail(), user.getPassword());

        if (dbUser != null) {
            return jwtUtil.generateToken(user.getEmail());
        }

        return "Invalid";
    }

    // ✅ REGISTER
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.register(user);
    }
}