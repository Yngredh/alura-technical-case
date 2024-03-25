package com.aluratechnicalcase.application.dto;

import com.aluratechnicalcase.domain.entity.Role;

public record UserResponseDTO(
        String name,
        String email,
        Role role
) {}
