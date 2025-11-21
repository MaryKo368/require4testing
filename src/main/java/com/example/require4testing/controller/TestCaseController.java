package com.example.require4testing.controller;

import com.example.require4testing.model.Requirement;
import com.example.require4testing.model.TestCase;
import com.example.require4testing.repository.RequirementRepository;
import com.example.require4testing.repository.TestCaseRepository; // <-- WICHTIG
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/testcases")
@CrossOrigin(origins = "*")
public class TestCaseController {

    private final TestCaseRepository testCaseRepository;
    private final RequirementRepository requirementRepository;

    public TestCaseController(TestCaseRepository testCaseRepository,
                              RequirementRepository requirementRepository) {
        this.testCaseRepository = testCaseRepository;
        this.requirementRepository = requirementRepository;
    }

    @GetMapping
    public List<TestCase> getAll() {
        return testCaseRepository.findAll();
    }

    @PostMapping
    public TestCase create(@RequestBody TestCaseRequest request) {
        Requirement req = requirementRepository.findById(request.requirementId())
                .orElseThrow(() -> new RuntimeException("Requirement not found"));

        TestCase testCase = new TestCase(request.expectedResult(), req);
        return testCaseRepository.save(testCase);
    }

    @GetMapping("/requirement/{id}")
    public List<TestCase> getByRequirement(@PathVariable Long id) {
        Requirement req = requirementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Requirement not found"));

        return testCaseRepository.findByRequirement(req);
    }

    public record TestCaseRequest(String expectedResult, Long requirementId) {}
}
