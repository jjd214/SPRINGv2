package com.example.work.company;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    public CompanyService(CompanyRepository companyRepository, CompanyMapper companyMapper) {
        this.companyRepository = companyRepository;
        this.companyMapper = companyMapper;
    }

    @Transactional
    public CompanyResponseDto create(CompanyRequestDto dto) {
        var company = companyMapper.toEntity(dto);
        var saved = companyRepository.save(company);
        return companyMapper.toDto(saved);
    }

    @Transactional(readOnly = true)
    public CompanyResponseDto find(Long id) {
        var company = companyRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Company not found"));
        return companyMapper.toDto(company);
    }

    @Transactional(readOnly = true)
    public List<CompanyResponseDto> findAll(long pageSize, int pageNumber) {
        return companyRepository.findAll()
                .stream()
                .skip((pageNumber - 1) * pageSize)
                .limit(pageSize)
                .map(companyMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public CompanyResponseDto update(Long companyId, CompanyRequestDto dto) {
        var company = companyRepository.findById(companyId)
                .orElseThrow(()-> new EntityNotFoundException("Company not found"));

        company.setName(dto.name());
        company.setIndustry(dto.industry());

        var saved = companyRepository.save(company);
        return companyMapper.toDto(saved);
    }

    @Transactional
    public void delete(Long id) {
        companyRepository.deleteById(id);
    }
}
