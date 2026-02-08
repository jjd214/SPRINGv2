package com.example.work.skill;

import jakarta.validation.constraints.NotBlank;

public record SkillRequestDto(
        @NotBlank String name,
        String description
) {
}
