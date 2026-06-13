package com.hammad.backend.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hammad.backend.dto.RegisterRequest;
import com.hammad.backend.dto.RegisterResponse;
import com.hammad.backend.model.User;
import com.hammad.backend.repository.UserRepo;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public RegisterResponse register(RegisterRequest request) {
        if (userRepo.existsByUsername(request.username())) {
            throw new IllegalArgumentException("Username already exists");
        }

        User user = new User();
        user.setUsername(request.username());
        user.setRole("USER");
        user.setPassword(passwordEncoder.encode(request.password()));
        User savedUser = userRepo.save(user);
        return new RegisterResponse(savedUser.getId(), savedUser.getUsername(), savedUser.getRole());
    }
}
