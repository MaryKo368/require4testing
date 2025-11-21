package com.example.require4testing.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Requirement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 2000)
    private String description;

    @OneToMany(mappedBy = "requirement", cascade = CascadeType.ALL)
    @JsonIgnore   // WICHTIG!
    private List<TestCase> testCases = new ArrayList<>();

    public Requirement() {}

    public Requirement(String title, String description) {
        this.title = title;
        this.description = description;
    }

    // GETTER und SETTER

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<TestCase> getTestCases() {
        return testCases;
    }

    public void setTestCases(List<TestCase> testCases) {
        this.testCases = testCases;
    }
}
