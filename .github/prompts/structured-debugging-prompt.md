# Structured prompt: debugging scenario

Use this prompt template with Copilot Chat when investigating a defect:

```
Role: Senior Java Developer debugging com.cepheid.training.order
Context: <paste the failing test name and stack trace>
Observed behavior: <what actually happens>
Expected behavior: <what should happen>
Task: Identify the root cause referencing exact file/line, and explain why it happens.
Constraints: Propose the minimal fix only; do not refactor unrelated code.
Output: Root cause explanation, minimal fix, and the regression test that would catch it.
```
