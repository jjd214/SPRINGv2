package com.example.work.team;

import com.example.work.company.Company;
import com.example.work.member.Member;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class TeamMapper {

    public Team toEntity(TeamRequestDto dto, Company company) {
        return new Team()
                .setName(dto.name())
                .setDescription(dto.description())
                .setCompany(company);
    }

    public TeamResponseDto toDto(Team team) {
        return new TeamResponseDto(
                team.getId(),
                team.getName(),
                team.getDescription(),
                team.getCompany() != null ? toCompanySummaryDto(team.getCompany()) : null,
                team.getMembers() != null ?
                        team.getMembers()
                                .stream()
                                .map(this::toMemberSummaryDto)
                                .collect(Collectors.toList())
                        : null,
                team.getUpdatedAt(),
                team.getCreatedAt()
        );
    }

    private CompanySummaryDto toCompanySummaryDto(Company company) {
        return new CompanySummaryDto(
                company.getId(),
                company.getName(),
                company.getIndustry(),
                company.getUpdatedAt(),
                company.getCreatedAt()
        );
    }

    private MemberSummaryDto toMemberSummaryDto(Member member) {
        return new MemberSummaryDto(
                member.getId(),
                member.getFullName(),
                member.getEmail(),
                member.getUpdatedAt(),
                member.getCreatedAt()
        );
    }
}
