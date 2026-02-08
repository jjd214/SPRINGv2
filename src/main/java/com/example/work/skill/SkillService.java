package com.example.work.skill;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SkillService {

    private final SkillRepository skillRepository;
    private final SkillMapper skillMapper;

    public SkillService(SkillRepository skillRepository, SkillMapper skillMapper) {
        this.skillRepository = skillRepository;
        this.skillMapper = skillMapper;
    }

    @Transactional
    public SkillResponseDto create(SkillRequestDto dto) {
        var skill = skillMapper.toEntity(dto);
        var saved = skillRepository.save(skill);
        return skillMapper.toDto(saved);
    }

    @Transactional(readOnly = true)
    public SkillResponseDto find(Long id) {
        var skill = skillRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Skill not found"));
        return skillMapper.toDto(skill);
    }

    @Transactional(readOnly = true)
    public List<SkillResponseDto> findAll(long pageSize, int pageNumber) {
        return skillRepository.findAll()
                .stream()
                .skip((pageNumber - 1) * pageSize)
                .limit(pageSize)
                .map(skillMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public SkillResponseDto update(Long skillId, SkillRequestDto dto) {
        var skill = skillRepository.findById(skillId)
                .orElseThrow(()-> new EntityNotFoundException("Skill not found"));

        skill.setName(dto.name());
        skill.setDescription(dto.description());

        var saved = skillRepository.save(skill);
        return skillMapper.toDto(saved);
    }

    @Transactional
    public void delete(Long id) {
        skillRepository.deleteById(id);
    }
}
