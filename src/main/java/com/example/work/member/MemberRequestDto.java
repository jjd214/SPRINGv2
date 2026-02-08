package com.example.work.member;

import jakarta.validation.constraints.NotBlank;

public record MemberRequestDto(
        @NotBlank String fullName,
        @NotBlank String email,
        Long teamId
) {
}
