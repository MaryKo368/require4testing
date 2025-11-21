package com.example.require4testing.controller;

import com.example.require4testing.model.Requirement;
import com.example.require4testing.model.TestCase;
import com.example.require4testing.repository.RequirementRepository;
import com.example.require4testing.repository.TestCaseRepository; // <-- WICHTIGER IMPORT!
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WebController {

    private final RequirementRepository requirementRepository;
    private final TestCaseRepository testCaseRepository;

    public WebController(RequirementRepository requirementRepository,
                         TestCaseRepository testCaseRepository) {
        this.requirementRepository = requirementRepository;
        this.testCaseRepository = testCaseRepository;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("requirements", requirementRepository.findAll());
        return "requirements";
    }

    @GetMapping("/requirements/new")
    public String newRequirementForm() {
        return "requirement_form";
    }

    @PostMapping("/requirements/new")
    public String createRequirement(@RequestParam String title,
                                    @RequestParam String description) {
        requirementRepository.save(new Requirement(title, description));
        return "redirect:/";
    }

    @GetMapping("/requirements/{id}")
    public String showRequirement(@PathVariable Long id, Model model) {
        Requirement req = requirementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Requirement not found"));

        model.addAttribute("requirement", req);
        model.addAttribute("testcases", req.getTestCases());

        return "testcases";
    }

    @PostMapping("/requirements/{id}/testcases/new")
    public String createTestCase(@PathVariable Long id,
                                 @RequestParam String expectedResult) {

        Requirement req = requirementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Requirement not found"));

        TestCase tc = new TestCase(expectedResult, req);
        testCaseRepository.save(tc);

        return "redirect:/requirements/" + id;
    }
}
