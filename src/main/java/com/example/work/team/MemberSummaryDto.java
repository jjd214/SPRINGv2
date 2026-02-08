package com.example.work.team;

import java.time.LocalDateTime;

public record MemberSummaryDto(
        Long id,
        String fullName,
        String email,
        LocalDateTime updatedAt,
        LocalDateTime createdAt
) {
}
