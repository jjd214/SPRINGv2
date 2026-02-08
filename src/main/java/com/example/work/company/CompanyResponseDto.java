package com.example.work.company;

import java.time.LocalDateTime;
import java.util.List;

public record CompanyResponseDto(
        Long id,
        String name,
        String industry,
        List<TeamSummaryDto> teams,
        LocalDateTime updatedAt,
        LocalDateTime createdAt
) {
}
