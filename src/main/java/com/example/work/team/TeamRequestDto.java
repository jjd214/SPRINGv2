package com.example.work.team;

import jakarta.validation.constraints.NotBlank;

public record TeamRequestDto(
        @NotBlank String name,
        String description,
        Long companyId
) {
}
