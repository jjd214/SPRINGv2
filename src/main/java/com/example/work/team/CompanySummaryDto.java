package com.example.work.team;


import java.time.LocalDateTime;
import java.util.List;

public record CompanySummaryDto(
        Long id,
        String name,
        String industry,
        LocalDateTime updatedAt,
        LocalDateTime createdAt
) {
}
