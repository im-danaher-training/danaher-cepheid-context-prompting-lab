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

## IntelliJ Setup
1. Open IntelliJ IDEA and choose **File > Open**, then select this repository folder.
2. Trust the project when prompted and let IntelliJ auto-import the Maven project.
3. Confirm the Project SDK is Java 17 in **File > Project Structure > Project**.
4. Open the **Maven** tool window (right sidebar) to run 	est/erify goals.
5. Open **GitHub Copilot Chat** from the right tool bar, keep the relevant file active so Copilot has context.

## Scenario
CepheidDx is modernizing its diagnostic order-management workflow. You are a developer joining the project mid-stream and must ramp up quickly using GitHub Copilot in IntelliJ.

## Starting Point
The `com.cepheid.training.order` package contains `Customer`, `Product`, `Order`, `OrderLine`, `ValidationService`, `OrderRepository`, and `OrderService`. Some requirements in `ValidationService` and `OrderService` are intentionally left ambiguous in comments.

## Hands-on Tasks
1. Open the repository in IntelliJ and ask Copilot Chat to explain the overall architecture of the `order` package.
2. Use the structured prompt in `.github/prompts/structured-migration-prompt.md` to ask Copilot for a plan to support partial order fulfillment.
3. Use the structured prompt in `.github/prompts/structured-debugging-prompt.md` on the failing scenario you construct (an order with a zero-quantity line).
4. Create or refine `.github/instructions/copilot-instructions.md` with one additional project-specific rule you find useful.
5. Re-run one of your earlier prompts after adding the instructions file and compare Copilot's response quality.
6. Compare an unstructured prompt ("fix the order stuff") with a structured prompt (using the template) and note the difference in usefulness.

## Validation
Run from the IntelliJ **Terminal** tab (Alt+F12) or the Maven tool window:

```bash
mvn test
```

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
- `.github/instructions/copilot-instructions.md` â€” always-on project rules Copilot applies automatically.
- `.github/prompts/structured-migration-prompt.md` and `structured-debugging-prompt.md` â€” reusable templates.

**Quick prompt (token-efficient migration ask):**
```
Role: Java maintainer of com.cepheid.training.order
Task: Plan support for partial order fulfillment
Constraints: keep public signatures; add JUnit 5 tests
Output: 3-step plan, no code yet
```

**Optional stretch tasks (build these yourself â€” not provided):**
- Custom agent: create `.github/agents/order-migration-agent.agent.md` defining a persona that plans safe, incremental changes to the order package.
- Skill: create `.github/skills/structured-prompting/SKILL.md` capturing a reusable checklist for writing short Role/Task/Constraints/Output prompts.
- `AGENTS.md`: optionally add a root-level file summarizing repo conventions for cross-tool agent compatibility (Copilot CLI and other agentic tools read this file).

**Enterprise tip:** Enterprise Copilot usage favors small, structured, reusable prompts over long free-form ones. State `Role / Task / Constraints / Output` in under ~5 lines â€” this keeps token usage low and responses focused, which matters when Copilot is used constantly across a team.
