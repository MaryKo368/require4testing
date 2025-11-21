package com.example.require4testing.controller;

import com.example.require4testing.model.Requirement;
import com.example.require4testing.repository.RequirementRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/requirements")
@CrossOrigin(origins = "*")
public class RequirementController {

    private final RequirementRepository requirementRepository;

    public RequirementController(RequirementRepository requirementRepository) {
        this.requirementRepository = requirementRepository;
    }

    @GetMapping
    public List<Requirement> getAll() {
        return requirementRepository.findAll();
    }

    @PostMapping
    public Requirement create(@RequestBody Requirement requirement) {
        return requirementRepository.save(requirement);
    }
}
