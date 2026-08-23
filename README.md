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
   Role: Senior Java Developer onboarding onto this codebase
   Context: com.cepheid.training.order package (Customer, Product, Order, OrderLine,
   ValidationService, OrderRepository, OrderService)
   Task: Explain the overall architecture — each class's responsibility and how they
   collaborate to process an order end to end
   Constraints: Read-only explanation; do not propose or make code changes
   Output: A short narrative plus a one-line responsibility summary per class
   ```
   Example of the kind of answer this prompt should produce:
   ```
   - Customer / Product: plain data holders for who is ordering and what is available.
   - Order / OrderLine: the order aggregate and its line items (product + quantity).
   - ValidationService: decides if an Order is valid (positive quantities, stock available).
   - OrderRepository: persists Orders.
   - OrderService: orchestrates submit() — validates, sets status, then saves.
   ```
2. Paste this migration prompt into IntelliJ's Copilot Chat window (also saved at `.github/prompts/structured-migration-prompt.md`):
   ```
   Role: Senior Java Developer maintaining com.cepheid.training.order
   Context: com.cepheid.training.order package models a diagnostic order workflow
   Task: Add support for partial order fulfillment
   Constraints:
   - Keep existing public method signatures unless explicitly told otherwise
   - Update or add JUnit 5 tests for any new behavior
   - Do not touch unrelated classes
   Output: The smallest change set with a short rationale, then the diff
   ```
3. Paste this debugging prompt on the failing scenario you construct (an order with a zero-quantity line), also saved at `.github/prompts/structured-debugging-prompt.md`:
   ```
   Role: Senior Java Developer debugging com.cepheid.training.order
   Context: <paste the failing test name and stack trace>
   Observed behavior: <what actually happens>
   Expected behavior: <what should happen>
   Task: Identify the root cause, referencing the exact file and line, and explain
   why it happens
   Constraints: Propose the minimal fix only; do not refactor unrelated code
   Output: Root cause explanation, minimal fix, and the regression test that would
   catch it
   ```
   Filled-in example (copy, adapt, and paste this if you don't want to construct your own failing test):
   ```
   Role: Senior Java Developer debugging com.cepheid.training.order
   Context: rejectsZeroQuantityLine (OrderServiceTest) — order = new Order("ORD-3", customer,
   List.of(new OrderLine(product, 0))); assertEquals(OrderStatus.REJECTED, result.getStatus());
   Observed behavior: The order is unexpectedly REJECTED even though the product has stock.
   Expected behavior: A zero-quantity line should be rejected, but I want to confirm this is
   intentional and not a validation bug hiding a different issue.
   Task: Identify the root cause, referencing the exact file and line, and explain
   why it happens
   Constraints: Propose the minimal fix only; do not refactor unrelated code
   Output: Root cause explanation, minimal fix, and the regression test that would
   catch it
   ```
4. Create or refine `.github/instructions/copilot-instructions.md` with one additional project-specific rule you find useful. Sample prompt to draft the rule:
   ```
   Role: Senior Java Developer authoring Copilot guardrails
   Context: .github/instructions/copilot-instructions.md governs Copilot's behavior
   in this repo
   Task: Propose one additional project-specific rule based on a mistake Copilot
   just made in this codebase
   Constraints: One rule only; must be concrete and enforceable, not generic advice
   Output: A single markdown bullet ready to paste into the instructions file
   ```
   Example of a good rule this prompt might produce:
   ```
   - Never mark an OrderLine quantity check as valid without also checking it against
     Product.getAvailableStock(); the two conditions must always be evaluated together.
   ```
5. Re-run one of your earlier prompts after adding the instructions file and compare Copilot's response quality.
6. Compare an unstructured prompt and a structured prompt, and note the difference in usefulness. Sample unstructured prompt (deliberately vague, for contrast only — this is the anti-pattern, not the style to copy):
   ```
   fix the order stuff
   ```
   Then re-ask using the structured migration prompt template from step 2 and compare the two responses for accuracy, scope discipline, and how much back-and-forth clarification each one needed.

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
Role: Senior Java Developer maintaining com.cepheid.training.order
Task: Plan support for partial order fulfillment
Constraints: Keep public signatures; add JUnit 5 tests; do not touch unrelated classes
Output: 3-step plan, no code yet
```

**Step-by-step: create these yourself (not provided)**

### 1. Custom agent — beginner steps

A custom agent is a Markdown file with a `name`/`description` header and a plain-text persona/prompt body. Files use the `.agent.md` extension (VS Code and JetBrains) and live at `.github/agents/AGENT-NAME.agent.md`.

1. In IntelliJ's Project view, right-click the `.github` folder > **New > Directory**, name it `agents` (skip if it already exists).
2. Right-click the new `agents` folder > **New > File**, name it exactly `order-migration-agent.agent.md`.
3. Use the sample prompt below in Copilot Chat to have it draft the file's content — review the output, then paste it into the empty file yourself and save (Ctrl+S). Don't just copy a finished file; the point of this exercise is to draft, review, and adjust the persona and fixed steps yourself.
   ```
   Role: Senior Java Developer designing a GitHub Copilot custom agent
   Context: .github/agents/order-migration-agent.agent.md does not exist yet
   Task: Draft the agent definition for a persona that plans safe, incremental
   changes to com.cepheid.training.order
   Constraints:
   - Fixed step sequence: 1) restate the goal, 2) list affected classes,
     3) propose the smallest change set, 4) list tests to add/update, 5) show the diff
   - Never change public method signatures without flagging it
   - Never touch unrelated classes
   Output: The complete agent definition file content
   ```
4. The file needs a YAML frontmatter block with `name` and `description`, followed by a plain-text prompt body describing the persona, its fixed step sequence, and its constraints — verify Copilot's draft includes all three before saving.
5. **How to use it:** Open **GitHub Copilot Chat**, click the agent/mode picker (usually near the chat input box) and select **order-migration-agent** from the list. Then type your request as normal, for example:
   ```
   Plan support for partial order fulfillment when stock is insufficient.
   ```
   If your Copilot version doesn't show a picker, mention the agent by name at the start of the prompt (e.g. "Using the order-migration-agent persona, ...").
6. **Expected result:** The reply follows the five fixed steps instead of jumping straight to code, and calls out if the plan would require changing a public signature.

### 2. Skill — beginner steps

A skill is a folder containing a `SKILL.md` file with a `name`/`description` header and instructions Copilot should follow whenever the skill topic comes up. Skills live at `.github/skills/<skill-name>/SKILL.md`.

1. In IntelliJ's Project view, right-click the `.github` folder > **New > Directory**, name it `skills` (skip if it already exists).
2. Inside `skills`, create another directory named `structured-prompting` (lowercase, hyphenated).
3. Inside `structured-prompting`, create a file named exactly `SKILL.md`.
4. Use the sample prompt below in Copilot Chat to have it draft the file's content — review the output, then paste it into the empty file yourself and save. Adjust the wording so it genuinely reflects the Role/Task/Constraints/Output convention you want enforced, rather than accepting the first draft verbatim.
   ```
   Role: Senior Java Developer documenting a reusable Copilot skill
   Context: .github/skills/structured-prompting/SKILL.md does not exist yet
   Task: Write SKILL.md describing when to use short Role/Task/Constraints/Output
   prompts in this repo
   Constraints: Include explicit trigger conditions, the prompt conventions, and
   one worked example using the order package
   Output: The complete SKILL.md file content
   ```
5. The file needs a YAML frontmatter block with `name` and `description` (the `description` is what Copilot matches against your prompts to decide when to apply the skill), followed by the concrete conventions and one worked example — verify Copilot's draft includes all three before saving.
6. **How to use it:** Skills are picked up automatically — Copilot scans `.github/skills/` and applies a skill when your prompt matches its `description`. You don't select it manually; just ask a matching question in Copilot Chat, for example:
   ```
   Role: Senior Java Developer maintaining com.cepheid.training.order
   Task: Using the structured-prompting skill, plan the partial-fulfillment
   change to OrderService
   Constraints: Follow the skill's Role/Task/Constraints/Output convention
   Output: A 3-step plan only, no code yet
   ```
7. **Expected result:** The response is itself formatted as Role/Task/Constraints/Output (or clearly follows those conventions) — that confirms the skill was actually applied, not just a coincidence.

### 3. Root `AGENTS.md` (optional)

Summarize build/test commands and where the `.github/` customization files live, for cross-tool agent compatibility (Copilot CLI and other agentic tools read this file).
- Sample prompt to draft it:
  ```
  Role: Senior Java Developer documenting cross-tool agent context
  Context: Root AGENTS.md does not exist yet; this project builds with Maven
  and JDK 17
  Task: Generate AGENTS.md summarizing the build/test commands and where the
  .github/instructions, .github/prompts, .github/agents, and .github/skills
  customization files live
  Constraints: Keep it under one page; reference exact file paths
  Output: The complete AGENTS.md file content
  ```

**Enterprise tip:** Enterprise Copilot usage favors small, structured, reusable prompts over long free-form ones. State `Role / Task / Constraints / Output` in under ~5 lines - this keeps token usage low and responses focused, which matters when Copilot is used constantly across a team.

## Practical Model-Selection Guide
Copilot Chat lets you choose the model that answers your prompt from the model picker in the chat
input box. Use the table below to match the task to the model family that fits it best.

| Task | Recommended model | Why |
|---|---|---|
| Simple code completion | GPT-5 mini / Haiku 4.5 | Fast and inexpensive |
| Boilerplate / CRUD | GPT-5 mini / Haiku 4.5 | Doesn't need deep reasoning |
| Documentation / README | Haiku 4.5 / GPT-5 mini | Fast, straightforward |
| Unit-test generation | Haiku 4.5 / Sonnet 4.6 | Good balance of speed and reasoning |
| Bug fixing | Sonnet 4.6 / GPT-5.4 | Better investigation and reasoning |
| Complex debugging | GPT-5.4 / GPT-5.6 Sol | Strong multi-step reasoning |
| Large refactoring | Sonnet 4.6 / GPT-5.6 Sol | Better multi-file/context understanding |
| Architecture/design | GPT-5.6 Sol / GPT-5.4 | Deep technical reasoning |
| Legacy modernization | Sonnet 4.6 / GPT-5.6 Sol | Strong cross-file and cross-language reasoning |
| Agentic implementation | GPT-5.3-Codex / Sonnet 4.6 | Designed for agentic software development |
| Large codebase analysis | GPT-5.6 Sol / Kimi K3 | Long-context, complex analysis |
| Screenshot/UI analysis | GPT-5 mini / Sonnet 4.6 / Gemini 3.1 Pro | Multimodal capability |
| Quick CLI operations | Haiku 4.5 / Flash models | Low latency |
| Code-focused CLI work | GPT-5.3-Codex | Optimized for coding |
| Very difficult technical problem | GPT-5.6 Sol / highest reasoning model available | Maximum reasoning capability |

Guidance:
- Use a lighter model for short, well-scoped asks such as one method, one file, or routine copy.
- Switch to a deeper-reasoning model for tasks that span multiple classes, need root-cause analysis,
  or require architecture-level thinking.
- Use an agentic/coding-focused model when you want Copilot to work more autonomously across files.
- GitHub currently groups GPT-5.3-Codex for agentic software development, GPT-5.4 for deep
  reasoning/debugging, Sonnet 4.6 for complex coding/agent tasks, and lighter models such as
  Haiku 4.5 for everyday coding.
- Model names and strengths change over time. Check the model picker in your IDE and GitHub's
  [AI model comparison](https://docs.github.com/en/copilot/reference/ai-models/model-comparison)
  doc before your session.
