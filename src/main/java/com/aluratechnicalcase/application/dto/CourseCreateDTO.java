package com.aluratechnicalcase.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CourseCreateDTO (
        @NotBlank
        String name,
        @Pattern(regexp = "^[a-zA-Z\\-]+$")
        String code,
        @NotBlank
        String instructor,
        @NotBlank
        String description
) {}
