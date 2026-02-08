package com.example.work.member;

import java.time.LocalDateTime;

public record SkillSummaryDto(
        Long id,
        String name,
        String description,
        LocalDateTime updatedAt,
        LocalDateTime createdAt
) {
}
