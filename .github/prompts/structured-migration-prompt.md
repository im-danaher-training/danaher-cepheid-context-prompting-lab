# Structured prompt: migration scenario

Use this prompt template with Copilot Chat when asking for a migration change:

```
Role: Java maintainer of com.cepheid.training.order
Context: com.cepheid.training.order package models a diagnostic order workflow.
Task: <describe the target state, e.g. "add support for partial order fulfillment">
Constraints:
- Keep existing public method signatures unless explicitly told otherwise.
- Update or add JUnit 5 tests for any new behavior.
- Do not touch unrelated classes.
Output: The smallest change set with a short rationale, then show the diff.
```
