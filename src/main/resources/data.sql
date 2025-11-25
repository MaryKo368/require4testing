-- Requirement anlegen
INSERT INTO requirement (id, title, description) VALUES
    (1, 'Login Feature', 'Benutzer soll sich einloggen können');

-- Testfall zu Requirement speichern
INSERT INTO test_case (id, requirement_id, expected_result) VALUES
    (1, 1, 'Login funktioniert erfolgreich');

-- Testlauf anlegen
INSERT INTO test_run (id, name, execution_date) VALUES
    (1, 'Testlauf 1', CURRENT_DATE);

-- Test Execution anlegen
INSERT INTO test_execution (id, test_case_id, test_run_id, status, tester) VALUES
    (1, 1, 1, 'OPEN', 'Maria');
