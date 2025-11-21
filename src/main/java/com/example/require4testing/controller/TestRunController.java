package com.example.require4testing.controller;

import com.example.require4testing.model.TestCase;
import com.example.require4testing.model.TestExecution;
import com.example.require4testing.model.TestRun;
import com.example.require4testing.repository.TestCaseRepository;
import com.example.require4testing.repository.TestExecutionRepository;
import com.example.require4testing.repository.TestRunRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/testruns")
public class TestRunController {

    private final TestRunRepository runRepo;
    private final TestCaseRepository caseRepo;
    private final TestExecutionRepository execRepo;

    /** LISTE */
    @GetMapping
    public String list(Model model) {
        model.addAttribute("testruns", runRepo.findAll());
        return "testruns/list";
    }

    /** NEUER TESTLAUF (FORMULAR) */
    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("testrun", new TestRun());
        return "testruns/new";
    }

    /** NEUER TESTLAUF SPEICHERN */
    @PostMapping("/new")
    public String create(@ModelAttribute TestRun testrun) {

        if (testrun.getExecutionDate() == null) {
            testrun.setExecutionDate(java.time.LocalDate.now());
        }

        runRepo.save(testrun);
        return "redirect:/testruns";
    }

    /** DETAILS */
    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {

        TestRun run = runRepo.findById(id).orElse(null);
        if (run == null) {
            return "redirect:/testruns";
        }

        model.addAttribute("testrun", run);
        model.addAttribute("testcases", caseRepo.findAll()); // Dropdown
        return "testruns/detail";
    }

    /** TESTCASE ZUWEISEN */
    @PostMapping("/{id}/assign")
    public String assignTestcase(@PathVariable Long id,
                                 @RequestParam Long testCaseId,
                                 @RequestParam String tester) {

        TestRun run = runRepo.findById(id).orElse(null);
        if (run == null) {
            return "redirect:/testruns";
        }

        TestCase tc = caseRepo.findById(testCaseId).orElse(null);
        if (tc == null) {
            return "redirect:/testruns/" + id;
        }

        if (tester == null || tester.trim().isEmpty()) {
            tester = "Unbekannt";
        }

        TestExecution exec = new TestExecution();
        exec.setTestRun(run);
        exec.setTestCase(tc);
        exec.setTester(tester);

        execRepo.save(exec);

        return "redirect:/testruns/" + id;
    }
}
