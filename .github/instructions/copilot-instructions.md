# Copilot instructions

- This is an IntelliJ IDEA + Maven + Java 17 training repository for a Danaher/Cepheid workshop.
- All business data is fictional training data (diagnostic/order-management scenarios). Never introduce real patient, customer, or production data.
- Prefer the smallest safe change that satisfies the task. Do not perform unrelated refactoring.
- Preserve existing public method signatures unless the task explicitly asks for a signature change.
- Add or update JUnit 5 tests for any behavior change.
- Explain non-obvious changes briefly in the PR/commit description, not with block comments in code.
- Validate every change by running `mvn test` before considering the task complete.
