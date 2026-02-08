package com.example.work.company;

import jakarta.validation.constraints.NotBlank;

public record CompanyRequestDto(
        @NotBlank String name,
        @NotBlank String industry
) {
}
