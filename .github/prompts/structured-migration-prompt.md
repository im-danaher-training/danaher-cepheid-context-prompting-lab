# Structured prompt: migration scenario

Use this prompt template with Copilot Chat when asking for a migration change:

```
Context: com.cepheid.training.order package models a diagnostic order workflow.
Goal: <describe the target state, e.g. "add support for partial order fulfillment">
Constraints:
- Keep existing public method signatures unless explicitly told otherwise.
- Update or add JUnit 5 tests for any new behavior.
- Do not touch unrelated classes.
Ask: Propose the smallest change set with a short rationale, then show the diff.
```
