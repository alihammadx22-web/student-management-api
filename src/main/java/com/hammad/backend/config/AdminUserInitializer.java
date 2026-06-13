package com.hammad.backend.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.hammad.backend.model.User;
import com.hammad.backend.repository.UserRepo;

@Component
@ConditionalOnProperty(name = "app.admin.enabled", havingValue = "true")
public class AdminUserInitializer implements CommandLineRunner {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final String username;
    private final String password;

    public AdminUserInitializer(
            UserRepo userRepo,
            PasswordEncoder passwordEncoder,
            @Value("${app.admin.username}") String username,
            @Value("${app.admin.password}") String password) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.username = username;
        this.password = password;
    }

    @Override
    public void run(String... args) {
        if (password == null || password.isBlank()) {
            throw new IllegalStateException(
                    "ADMIN_PASSWORD must be set when app.admin.enabled=true");
        }

        if (userRepo.findByUsername(username) == null) {
            User admin = new User();
            admin.setUsername(username);
            admin.setPassword(passwordEncoder.encode(password));
            admin.setRole("ADMIN");
            userRepo.save(admin);
        }
    }
}
