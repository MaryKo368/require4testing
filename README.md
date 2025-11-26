# Require4Testing -- Fallstudie IPWA02-01

Eine vollständige Testmanagement-Webapplikation, entwickelt im Modul\
**IPWA02-01 -- Programmierung von industriellen Informationssystemen mit
Java EE**\
an der IU Internationale Hochschule.

**Alle MUST- und SHOULD-Anforderungen der Aufgabenstellung sind 100 %
umgesetzt.**

## 🚀 Features

-   Requirements erfassen und verwalten\
-   Testfälle erstellen und Requirements zuordnen\
-   Testläufe anlegen\
-   Testfälle einem Testlauf sowie einem Tester zuordnen *(SHOULD)*\
-   Testergebnisse (bestanden / nicht bestanden / blockiert) erfassen
    *(SHOULD)*

## 🛠 Technologien

-   Java 17\
-   Spring Boot 3\
-   Spring MVC + Thymeleaf\
-   Spring Data JPA + Hibernate\
-   H2 In-Memory-Datenbank\
-   Maven

## ⚡ Schnellstart

### Projekt klonen & starten

``` bash
git clone https://github.com/MaryKo368/require4testing.git
cd require4testing
mvn spring-boot:run
```

Die Anwendung ist anschließend erreichbar unter:\
**http://localhost:8080**

## 🗄️ H2-Datenbank-Konsole

**URL:** http://localhost:8080/h2-console\
**JDBC URL:** jdbc:h2:mem:testdb\
**User:** sa\
**Passwort:** *(leer)*

## 📱 Navigation (Hauptmenü)

-   Requirements verwalten\
-   Testfälle verwalten\
-   Testläufe verwalten

## 📸 Screenshots

-   `docs/screenshots/startseite.png`\
-   `docs/screenshots/testruns.png`\
-   `docs/screenshots/testfall-zuweisung.png`\
-   `docs/screenshots/test-execution.png`

## 📐 UML-Klassendiagramm

`docs/architecture/UML-Klassendiagramm.png`

## 📁 Projektstruktur (Auszug)

``` text
src/main/java/com/example/require4testing/
├── controller/   → Home-, Requirements-, TestCase-, TestRun-, TestExecutionController
├── model/        → Requirement, TestCase, TestRun, TestExecution
├── repository/   → JPA-Repositories
└── Require4testingApplication.java
```

## 👩‍💻 Autorin

**Marianna Kovaleva**\
IU Internationale Hochschule\
Modul IPWA02-01 -- Wintersemester 2025
