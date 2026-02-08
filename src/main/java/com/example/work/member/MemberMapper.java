package com.example.work.member;

import com.example.work.skill.Skill;
import com.example.work.team.Team;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class MemberMapper {

    public Member toEntity(MemberRequestDto dto, Team team) {
        return new Member()
                .setFullName(dto.fullName())
                .setEmail(dto.email())
                .setTeam(team);
    }

    public MemberResponseDto toDto(Member member) {
        return new MemberResponseDto(
                member.getId(),
                member.getFullName(),
                member.getEmail(),
                member.getTeam() != null ? toTeamSummaryDto(member.getTeam()) : null,
                member.getSkills() != null ?
                        member.getSkills()
                                .stream()
                                .map(this::toSkillSummaryDto)
                                .collect(Collectors.toList())
                        : null,
                member.getUpdatedAt(),
                member.getCreatedAt()
        );
    }

    public TeamSummaryDto toTeamSummaryDto(Team team) {
        return new TeamSummaryDto(
                team.getId(),
                team.getName(),
                team.getDescription(),
                team.getUpdatedAt(),
                team.getCreatedAt()
        );
    }

    public SkillSummaryDto toSkillSummaryDto(Skill skill) {
        return new SkillSummaryDto(
                skill.getId(),
                skill.getName(),
                skill.getDescription(),
                skill.getUpdatedAt(),
                skill.getCreatedAt()
        );
    }


}
