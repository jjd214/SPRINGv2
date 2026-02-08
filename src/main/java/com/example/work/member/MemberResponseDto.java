package com.example.work.member;

import java.time.LocalDateTime;
import java.util.List;

public record MemberResponseDto(
        Long id,
        String fullName,
        String email,
        TeamSummaryDto teams,
        List<SkillSummaryDto> skills,
        LocalDateTime updatedAt,
        LocalDateTime createdAt
) {
}
