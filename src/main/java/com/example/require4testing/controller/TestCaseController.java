package com.example.require4testing.controller;

import com.example.require4testing.model.TestCase;
import com.example.require4testing.model.Requirement;
import com.example.require4testing.repository.TestCaseRepository;
import com.example.require4testing.repository.RequirementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/testcases")
public class TestCaseController {

    private final TestCaseRepository caseRepo;
    private final RequirementRepository reqRepo;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("testcases", caseRepo.findAll());
        return "testcases/list";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("testcase", new TestCase());
        model.addAttribute("requirements", reqRepo.findAll());
        return "testcases/new";
    }

    @PostMapping("/new")
    public String create(@RequestParam String expectedResult,
                         @RequestParam Long requirementId) {

        Requirement req = reqRepo.findById(requirementId).orElse(null);
        if (req == null) return "redirect:/testcases";

        TestCase tc = new TestCase(expectedResult, req);
        caseRepo.save(tc);

        return "redirect:/testcases";
    }
}
