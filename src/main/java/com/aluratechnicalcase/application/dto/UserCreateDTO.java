package com.aluratechnicalcase.application.dto;

import com.aluratechnicalcase.domain.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UserCreateDTO (
        String name,
        @NotBlank
        @Pattern(regexp = "^[^0-9A-Z\\s]*$")
        String username,
        @Email
        String email,
        @NotBlank
        String password,
        Role role
) {}
