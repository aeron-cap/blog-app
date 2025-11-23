package com.example.blog_app.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
    @NotBlank(message = "Enter your Username")
    String username,

    @NotBlank(message = "Enter your Password")
    String password
) {
}
