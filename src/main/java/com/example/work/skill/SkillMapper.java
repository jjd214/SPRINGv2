package com.example.work.skill;

import org.springframework.stereotype.Component;

@Component
public class SkillMapper {

    public Skill toEntity(SkillRequestDto dto) {
        return new Skill()
                .setName(dto.name())
                .setDescription(dto.description());
    }

    public SkillResponseDto toDto(Skill skill) {
        return new SkillResponseDto(
                skill.getId(),
                skill.getName(),
                skill.getDescription(),
                skill.getUpdatedAt(),
                skill.getCreatedAt()
        );
    }
}
