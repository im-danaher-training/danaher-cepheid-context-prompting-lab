# Copilot Instructions

## Project Context
This repository is an IntelliJ IDEA + Maven + Java 17 training project used for a Danaher/Cepheid
Copilot workshop. It models a fictional diagnostic order-management workflow
(`com.cepheid.training.order`). All business data — customers, products, orders — is fictional
training data. Never introduce real patient, customer, or production data into this codebase.

## Coding Standards
- Prefer the smallest safe change that satisfies the task. Do not perform unrelated refactoring.
- Preserve existing public method signatures unless the task explicitly requires a signature change.
- Follow existing package conventions and class responsibilities rather than introducing new
  patterns for the same problem.

## Testing Requirements
- Add or update JUnit 5 tests for any behavior change.
- Validate every change by running `mvn test` before considering the task complete.

## Documentation and Communication
- Explain non-obvious changes briefly in the PR or commit description rather than with block
  comments in code.
- Keep code comments minimal and reserved for clarifying genuinely non-obvious logic.
