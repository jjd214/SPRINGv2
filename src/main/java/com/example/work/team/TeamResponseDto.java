package com.example.work.team;

import java.time.LocalDateTime;
import java.util.List;

public record TeamResponseDto(
        Long id,
        String name,
        String description,
        CompanySummaryDto company,
        List<MemberSummaryDto> members,
        LocalDateTime updatedAt,
        LocalDateTime createdAt
) {
}
