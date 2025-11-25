package com.example.require4testing.controller;

import com.example.require4testing.model.Requirement;
import com.example.require4testing.repository.RequirementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/requirements")
public class RequirementsController {

    private final RequirementRepository repo;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("requirements", repo.findAll());
        return "requirements/list";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("requirement", new Requirement());
        return "requirements/new";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute Requirement req) {
        repo.save(req);
        return "redirect:/requirements";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Requirement req = repo.findById(id).orElse(null);

        if (req == null) return "redirect:/requirements";

        model.addAttribute("requirement", req);
        return "requirements/detail";
    }
}
