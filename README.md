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
