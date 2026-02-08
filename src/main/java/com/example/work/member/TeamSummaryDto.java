package com.example.work.member;

import java.time.LocalDateTime;

public record TeamSummaryDto(
        Long id,
        String name,
        String description,
        LocalDateTime updatedAt,
        LocalDateTime createdAt
) {
}
