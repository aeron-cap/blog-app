package com.example.blog_app.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record BlogRequest(
    @NotBlank(message = "Title is Required")
    @Size(min = 3, message = "Title must be at least 3 characters.")
    String title,

    @NotBlank(message = "Content is Required")
    String content
) {
}
