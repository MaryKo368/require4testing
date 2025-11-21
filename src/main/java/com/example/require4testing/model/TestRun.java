package com.example.require4testing.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class TestRun {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate executionDate;

    @OneToMany(mappedBy = "testRun", cascade = CascadeType.ALL)
    private List<TestExecution> executions = new ArrayList<>();
}
