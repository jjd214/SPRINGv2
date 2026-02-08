package com.example.work.company;

import com.example.work.team.Team;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CompanyMapper {

    public Company toEntity(CompanyRequestDto dto) {
        return new Company()
                .setName(dto.name())
                .setIndustry(dto.industry());
    }

    public CompanyResponseDto toDto(Company company) {
        return new CompanyResponseDto(
                company.getId(),
                company.getName(),
                company.getIndustry(),
                company.getTeams() != null ?
                        company.getTeams()
                                .stream()
                                .map(this::toTeamSummaryDto)
                                .collect(Collectors.toList())
                        : null,
                company.getUpdatedAt(),
                company.getCreatedAt()
        );
    }

    private TeamSummaryDto toTeamSummaryDto(Team team) {
        return new TeamSummaryDto(
                team.getId(),
                team.getName(),
                team.getDescription(),
                team.getUpdatedAt(),
                team.getCreatedAt()
        );
    }
}
