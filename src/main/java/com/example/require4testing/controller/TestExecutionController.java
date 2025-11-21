package com.example.require4testing.controller;

import com.example.require4testing.model.TestExecution;
import com.example.require4testing.model.TestStatus;
import com.example.require4testing.repository.TestExecutionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/executions")
public class TestExecutionController {

    private final TestExecutionRepository execRepo;

    /**
     * DETAILSEITE EINER TEST EXECUTION (GET)
     */
    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {

        Optional<TestExecution> opt = execRepo.findById(id);
        if (opt.isEmpty()) {
            // Falls jemand eine ungültige ID aufruft
            return "redirect:/testruns";
        }

        TestExecution execution = opt.get();

        model.addAttribute("execution", execution);
        model.addAttribute("statuses", TestStatus.values());

        return "executions/detail";
    }

    /**
     * STATUS UPDATE FÜR EINE TEST EXECUTION (POST)
     */
    @PostMapping("/{id}")
    public String updateStatus(@PathVariable Long id,
                               @RequestParam("status") String statusValue) {

        Optional<TestExecution> opt = execRepo.findById(id);
        if (opt.isEmpty()) {
            return "redirect:/testruns";
        }

        TestExecution execution = opt.get();

        // Sicherer Enum-Parse mit Fallback
        try {
            TestStatus status = TestStatus.valueOf(statusValue);
            execution.setStatus(status);
        } catch (IllegalArgumentException e) {
            // falls irgendwas Komisches reinkommt
            execution.setStatus(TestStatus.OPEN);
        }

        execRepo.save(execution);

        // Falls aus irgendeinem Grund kein TestRun gesetzt ist
        if (execution.getTestRun() != null && execution.getTestRun().getId() != null) {
            return "redirect:/testruns/" + execution.getTestRun().getId();
        } else {
            return "redirect:/testruns";
        }
    }
}
