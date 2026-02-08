package com.example.work.member;

import com.example.work.skill.SkillRepository;
import com.example.work.team.Team;
import com.example.work.team.TeamRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;
    private final TeamRepository teamRepository;
    private final SkillRepository skillRepository;

    public MemberService(MemberRepository memberRepository,
                         MemberMapper memberMapper,
                         TeamRepository teamRepository,
                         SkillRepository skillRepository) {
        this.memberRepository = memberRepository;
        this.memberMapper = memberMapper;
        this.teamRepository = teamRepository;
        this.skillRepository = skillRepository;
    }

    @Transactional
    public MemberResponseDto create(MemberRequestDto dto) {
        Team team = null;
        if (dto.teamId() != null) {
            team = teamRepository.findById(dto.teamId())
                    .orElseThrow(()-> new EntityNotFoundException("Team not found"));
        }
        var member = memberMapper.toEntity(dto,team);
        var saved = memberRepository.save(member);
        return memberMapper.toDto(saved);
    }

    @Transactional(readOnly = true)
    public MemberResponseDto find(Long id) {
        var member = memberRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Member not found"));
        return memberMapper.toDto(member);
    }

    @Transactional(readOnly = true)
    public List<MemberResponseDto> findAll(long pageSize, int pageNumber) {
        return memberRepository.findAll()
                .stream()
                .skip((pageNumber - 1) * pageSize)
                .limit(pageSize)
                .map(memberMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public MemberResponseDto update(Long memberId, MemberRequestDto dto) {
        var member = memberRepository.findById(memberId)
                .orElseThrow(()-> new EntityNotFoundException("Member not found"));

        member.setFullName(dto.fullName());
        member.setEmail(dto.email());

        if (dto.teamId() != null) {
            var newTeam = teamRepository.findById(dto.teamId())
                    .orElseThrow(()-> new EntityNotFoundException("Team not found"));
            member.changeTeam(newTeam);
        }

        var saved = memberRepository.save(member);
        return memberMapper.toDto(saved);
    }

    @Transactional
    public void delete(Long id) {
        memberRepository.deleteById(id);
    }

    @Transactional
    public MemberResponseDto addSkill(Long memberId, Long skillId) {
        var member = memberRepository.findById(memberId)
                .orElseThrow(()-> new EntityNotFoundException("Member not found"));
        var skill = skillRepository.findById(skillId)
                .orElseThrow(()-> new EntityNotFoundException("Skill not found"));

        member.addSkill(skill);
        var saved = memberRepository.save(member);
        return memberMapper.toDto(saved);
    }
}
