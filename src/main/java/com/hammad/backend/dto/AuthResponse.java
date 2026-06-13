package com.hammad.backend.dto;

public record AuthResponse(
        String token,
        String tokenType,
        long expiresIn,
        String username,
        String role) {
}
