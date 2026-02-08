package com.example.work.skill;

import java.time.LocalDateTime;

public record SkillResponseDto(
        Long id,
        String name,
        String description,
        LocalDateTime updatedAt,
        LocalDateTime createdAt
) {
}
