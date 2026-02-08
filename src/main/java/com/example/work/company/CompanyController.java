package com.example.work.company;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyResponseDto create(@Valid @RequestBody CompanyRequestDto dto) {
        return companyService.create(dto);
    }

    @GetMapping("/{company-id}")
    public CompanyResponseDto find(@PathVariable("company-id") Long id) {
        return companyService.find(id);
    }

    @GetMapping
    public List<CompanyResponseDto> findAll(
            @RequestParam(defaultValue = "10") long pageSize,
            @RequestParam(defaultValue = "1") int pageNumber) {
        return companyService.findAll(pageSize,pageNumber);
    }

    @PutMapping("/{company-id}")
    public CompanyResponseDto update(
            @PathVariable("company-id") Long companyId,
            @Valid @RequestBody CompanyRequestDto dto) {
        return companyService.update(companyId,dto);
    }

    @DeleteMapping("/{company-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("company-id") Long id) {
        companyService.delete(id);
    }
}
