package com.example.require4testing.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class TestExecution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Verknüpfung Testlauf
    @ManyToOne
    private TestRun testRun;

    // Verknüpfung Testfall
    @ManyToOne
    private TestCase testCase;

    // Einfacher Tester-String (oder später ein User-Entity)
    private String tester;

    // Ergebnis
    @Enumerated(EnumType.STRING)
    private TestStatus status = TestStatus.OPEN;
}
