# Context Engineering & Structured Prompting Lab

## Objective
Learn to use GitHub Copilot inside **IntelliJ IDEA** to complete a realistic Danaher/Cepheid engineering task.

## Duration
40-45 minutes

## Prerequisites
- IntelliJ IDEA (Community or Ultimate)
- JDK 17+
- Maven 3.9+
- GitHub Copilot and GitHub Copilot Chat plugins enabled in IntelliJ
- Git

Before running Maven, make sure Java is installed and `JAVA_HOME` is set to your JDK directory and added to `PATH`.
On Windows PowerShell, this is typically:

```powershell
$env:JAVA_HOME = 'C:\Program Files\Microsoft\jdk-17.0.20.8-hotspot'
$env:PATH = "$env:JAVA_HOME\bin;$env:PATH"
```

## IntelliJ Setup
1. Open IntelliJ IDEA and choose **File > Open**, then select this repository folder.
2. Trust the project when prompted and let IntelliJ auto-import the Maven project.
3. Confirm the Project SDK is Java 17 in **File > Project Structure > Project**.
4. Open the **Maven** tool window (right sidebar) to run `test`/`verify` goals.
5. Open **GitHub Copilot Chat** from the right tool bar, keep the relevant file active so Copilot has context.

## Scenario
CepheidDx is modernizing its diagnostic order-management workflow. You are a developer joining the project mid-stream and must ramp up quickly using GitHub Copilot in IntelliJ.

## Starting Point
The `com.cepheid.training.order` package contains `Customer`, `Product`, `Order`, `OrderLine`, `ValidationService`, `OrderRepository`, and `OrderService`. Some requirements in `ValidationService` and `OrderService` are intentionally left ambiguous in comments.

## Hands-on Tasks
1. Open the repository in IntelliJ and ask Copilot Chat to explain the overall architecture of the `order` package. Sample prompt:
   ```
   Explain the overall architecture of the com.cepheid.training.order package:
   list each class, its responsibility, and how they collaborate to process an order.
   ```
2. Paste this migration prompt into IntelliJ's Copilot Chat window (also saved at `.github/prompts/structured-migration-prompt.md`):
   ```
   Context: com.cepheid.training.order package models a diagnostic order workflow.
   Goal: add support for partial order fulfillment
   Constraints:
   - Keep existing public method signatures unless explicitly told otherwise.
   - Update or add JUnit 5 tests for any new behavior.
   - Do not touch unrelated classes.
   Ask: Propose the smallest change set with a short rationale, then show the diff.
   ```
3. Paste this debugging prompt on the failing scenario you construct (an order with a zero-quantity line), also saved at `.github/prompts/structured-debugging-prompt.md`:
   ```
   Context: <paste the failing test name and stack trace>
   Observed behavior: <what actually happens>
   Expected behavior: <what should happen>
   Ask: Identify the root cause referencing exact file/line, explain why it happens,
   then propose the minimal fix and the test that would catch a regression.
   ```
4. Create or refine `.github/instructions/copilot-instructions.md` with one additional project-specific rule you find useful. Sample prompt to draft the rule:
   ```
   Suggest one additional project-specific rule for .github/instructions/copilot-instructions.md
   that would help Copilot avoid a mistake you just saw it make in this codebase.
   ```
5. Re-run one of your earlier prompts after adding the instructions file and compare Copilot's response quality.
6. Compare an unstructured prompt and a structured prompt, and note the difference in usefulness. Sample unstructured prompt (deliberately vague, for contrast only):
   ```
   fix the order stuff
   ```
   Then re-ask using the structured migration prompt template from step 2 and compare the two responses.

## Validation
Run from the IntelliJ **Terminal** tab (Alt+F12) or the Maven tool window:

```bash
mvn test
```

If Maven reports `JAVA_HOME is not defined correctly`, set the JDK first as shown in the prerequisites section, then rerun the command.

## Expected Result
You can articulate the architecture, produce a migration plan using structured prompting, and show a measurable improvement from adding repository instructions.

## Troubleshooting
- If Maven import fails in IntelliJ, use **Maven > Reload All Maven Projects**.
- If Copilot Chat has no context, make sure the relevant Java file is open and focused.

## Optional Challenge
Extend `OrderService` to support partial fulfillment when stock is insufficient, with a matching JUnit 5 test, using only structured prompts.

## Copilot Customization Guide

Enterprise teams reuse Copilot customization instead of re-typing context every time.

**Already provided:**
- `.github/instructions/copilot-instructions.md` - always-on project rules Copilot applies automatically.
- `.github/prompts/structured-migration-prompt.md` and `structured-debugging-prompt.md` - reusable templates.

**Quick prompt (token-efficient migration ask):**
```
Role: Java maintainer of com.cepheid.training.order
Task: Plan support for partial order fulfillment
Constraints: keep public signatures; add JUnit 5 tests
Output: 3-step plan, no code yet
```

**Step-by-step: create these yourself (not provided)**
1. Custom agent - create `.github/agents/order-migration-agent.agent.md`:
   - Persona: a persona that plans safe, incremental changes to the order package.
   - Before writing the file, design its fixed step sequence and any constraints on paper first.
   - Test it on one small, low-risk task before relying on it for the full lab task.
   - Sample prompt to draft it:
     ```
     Draft an agent definition for .github/agents/order-migration-agent.agent.md.
     Persona: a cautious Java maintainer of com.cepheid.training.order who plans
     safe, incremental changes.
     Fixed steps: 1) restate the goal, 2) list affected classes, 3) propose the
     smallest change set, 4) list the tests to add/update, 5) show the diff.
     Constraints: never change public method signatures without saying so; never
     touch unrelated classes.
     ```
2. Skill - create `.github/skills/structured-prompting/SKILL.md`:
   - Describe when it applies, the concrete conventions for writing short Role/Task/Constraints/Output prompts, and include one short example.
   - Reference the skill explicitly in a Copilot Chat prompt and confirm the output follows its conventions.
   - Sample prompt to draft it:
     ```
     Write a SKILL.md for .github/skills/structured-prompting/SKILL.md that
     describes when to use short Role/Task/Constraints/Output prompts in this
     repo, the conventions to follow, and includes one worked example using the
     order package.
     ```
   - Sample prompt to invoke it afterward:
     ```
     Using the structured-prompting skill, plan the partial-fulfillment change
     to OrderService.
     ```
3. Root `AGENTS.md` (optional) - summarize build/test commands and where the `.github/` customization files live, for cross-tool agent compatibility (Copilot CLI and other agentic tools read this file).
   - Sample prompt to draft it:
     ```
     Generate a root AGENTS.md summarizing how to build and test this project
     (Maven/JDK 17) and where the .github/instructions, .github/prompts,
     .github/agents, and .github/skills customization files live.
     ```

**Enterprise tip:** Enterprise Copilot usage favors small, structured, reusable prompts over long free-form ones. State `Role / Task / Constraints / Output` in under ~5 lines - this keeps token usage low and responses focused, which matters when Copilot is used constantly across a team.
