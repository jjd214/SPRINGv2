package com.example.work.team;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TeamResponseDto create(@Valid @RequestBody TeamRequestDto dto) {
        return teamService.create(dto);
    }

    @GetMapping("/{team-id}")
    public TeamResponseDto find(@PathVariable("team-id") Long id) {
        return teamService.find(id);
    }

    @GetMapping
    public List<TeamResponseDto> findAll(
            @RequestParam(defaultValue = "10") long pageSize,
            @RequestParam(defaultValue = "1") int pageNumber) {
        return teamService.findAll(pageSize,pageNumber);
    }

    @PutMapping("/{team-id}")
    public TeamResponseDto update(
            @PathVariable("team-id") Long teamId,
            @Valid @RequestBody TeamRequestDto dto) {
        return teamService.update(teamId,dto);
    }

    @DeleteMapping("/{team-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("team-id") Long id) {
        teamService.delete(id);
    }
}
