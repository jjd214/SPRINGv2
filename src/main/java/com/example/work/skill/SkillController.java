package com.example.work.skill;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skills")
public class SkillController {

    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SkillResponseDto create(@Valid @RequestBody SkillRequestDto dto) {
        return skillService.create(dto);
    }

    @GetMapping("/{skill-id}")
    public SkillResponseDto find(@PathVariable("skill-id") Long id) {
        return skillService.find(id);
    }

    @GetMapping
    public List<SkillResponseDto> findAll(
            @RequestParam(defaultValue = "10") long pageSize,
            @RequestParam(defaultValue = "1") int pageNumber) {
        return skillService.findAll(pageSize,pageNumber);
    }

    @PutMapping("{skill-id}")
    public SkillResponseDto update(
            @PathVariable("skill-id") Long skillId,
            @Valid @RequestBody SkillRequestDto dto) {
        return skillService.update(skillId,dto);
    }

    @DeleteMapping("/{skill-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("skill-id") Long id) {
        skillService.delete(id);
    }

}
