package com.aluratechnicalcase.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AvaliationDTO (@NotBlank String email,
                             @NotBlank String code,
                             @NotNull Integer value,
                             String message) {
}
