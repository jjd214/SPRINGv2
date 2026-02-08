package com.example.work.team;

import com.example.work.company.Company;
import com.example.work.company.CompanyRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;
    private final CompanyRepository companyRepository;

    public TeamService(TeamRepository teamRepository,
                       TeamMapper teamMapper,
                       CompanyRepository companyRepository) {
        this.teamRepository = teamRepository;
        this.teamMapper = teamMapper;
        this.companyRepository = companyRepository;
    }

    @Transactional
    public TeamResponseDto create(TeamRequestDto dto) {
        Company company = null;
        if (dto.companyId() != null) {
            company = companyRepository.findById(dto.companyId())
                    .orElseThrow(()-> new EntityNotFoundException("Company not found"));
        }
        var team = teamMapper.toEntity(dto, company);
        var saved = teamRepository.save(team);
        return teamMapper.toDto(saved);
    }

    @Transactional(readOnly = true)
    public TeamResponseDto find(Long id) {
        var team = teamRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Team not found"));
        return teamMapper.toDto(team);
    }

    @Transactional(readOnly = true)
    public List<TeamResponseDto> findAll(long pageSize, int pageNumber) {
        return teamRepository.findAll()
                .stream()
                .skip((pageNumber - 1) * pageSize)
                .limit(pageSize)
                .map(teamMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public TeamResponseDto update(Long teamId, TeamRequestDto dto) {
        var team = teamRepository.findById(teamId)
                .orElseThrow(()-> new EntityNotFoundException("Team not found"));

        team.setName(dto.name());
        team.setDescription(dto.description());

        if (dto.companyId() != null) {
            var newCompany = companyRepository.findById(dto.companyId())
                    .orElseThrow(()-> new EntityNotFoundException("Company not found"));
            team.changeCompany(newCompany);
        }

        var saved = teamRepository.save(team);
        return teamMapper.toDto(saved);
    }

    @Transactional
    public void delete(Long id) {
        teamRepository.deleteById(id);
    }

}
