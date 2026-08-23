# Structured prompt: debugging scenario

Use this prompt template with Copilot Chat when investigating a defect:

```
Context: <paste the failing test name and stack trace>
Observed behavior: <what actually happens>
Expected behavior: <what should happen>
Ask: Identify the root cause referencing exact file/line, explain why it happens,
then propose the minimal fix and the test that would catch a regression.
```
