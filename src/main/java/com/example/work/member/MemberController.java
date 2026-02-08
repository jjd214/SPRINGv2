package com.example.work.member;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MemberResponseDto create(@Valid @RequestBody MemberRequestDto dto) {
        return memberService.create(dto);
    }

    @GetMapping("/{member-id}")
    public MemberResponseDto find(@PathVariable("member-id") Long id) {
        return memberService.find(id);
    }

    @GetMapping
    public List<MemberResponseDto> findAll(
            @RequestParam(defaultValue = "10") long pageSize,
            @RequestParam(defaultValue = "1") int pageNumber) {
        return memberService.findAll(pageSize,pageNumber);
    }

    @PutMapping("/{member-id}")
    public MemberResponseDto update(
            @PathVariable("member-id") Long memberId,
            @Valid @RequestBody MemberRequestDto dto) {
        return memberService.update(memberId,dto);
    }

    @DeleteMapping("/{member-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("member-id") Long id) {
        memberService.delete(id);
    }

    @PostMapping("/{member-id}/skills/{skill-id}")
    @ResponseStatus(HttpStatus.CREATED)
    public MemberResponseDto addSkill(@PathVariable("member-id") Long memberId,
                                      @PathVariable("skill-id") Long skillId) {
        return memberService.addSkill(memberId,skillId);
    }
}
