package com.example.require4testing.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class TestCase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String expectedResult;

    @ManyToOne
    @JoinColumn(name = "requirement_id")
    @JsonIgnore   // verhindert Endlosschleifen in JSON
    private Requirement requirement;

    public TestCase() {}

    public TestCase(String expectedResult, Requirement requirement) {
        this.expectedResult = expectedResult;
        this.requirement = requirement;
    }

    // GETTER + SETTER
    public Long getId() {
        return id;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public void setExpectedResult(String expectedResult) {
        this.expectedResult = expectedResult;
    }

    public Requirement getRequirement() {
        return requirement;
    }

    public void setRequirement(Requirement requirement) {
        this.requirement = requirement;
    }
}
