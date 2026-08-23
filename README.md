# danaher-cepheid-context-prompting-lab

## Objective
This lab is designed for a Danaher/Cepheid Copilot workshop using IntelliJ IDEA as the primary IDE.

## Duration
40-45 minutes

## IntelliJ-first workflow
Participants should use IntelliJ IDEA throughout the exercise.

1. Open the repository root in IntelliJ using File > Open.
2. Allow Maven import when IntelliJ prompts.
3. Use the Maven tool window for clean test and package operations.
4. Run tests from the editor using the green triangle or Ctrl+Shift+F10.
5. Use GitHub Copilot Chat with the current file and project context open.
6. Keep the scope small, validate often, and review AI-generated changes before accepting them.

## Prerequisites
- IntelliJ IDEA
- JDK 17+
- Maven 3.9+
- GitHub Copilot and GitHub Copilot Chat
- Git

## Scenario
This project uses fictional diagnostic and order-management scenarios that resemble the healthcare technology domain without using production data or real patient information.

## Starting point
The codebase is intentionally small and realistic, with the relevant defect or learning objective already present. The participant uses Copilot to understand, fix, or improve it while following the lab instructions.

## Hands-on tasks
1. Open the repo in IntelliJ and inspect the project layout.
2. Ask Copilot to explain the architecture or issue in context.
3. Apply the requested change or refactor.
4. Add or improve tests relevant to the objective.
5. Run validation and confirm the expected behavior.

## Validation
Run from the IntelliJ terminal:

`ash
mvn test
`

## Expected result
The application compiles, tests pass, and the participant has completed the learning objective for this lab.

## Troubleshooting
- If Maven import fails, use Reload All Maven Projects in IntelliJ.
- If Java is not recognized, set the JDK in Project Structure.
- If Copilot output is weak, open the specific file and ask a more direct prompt with project context.

## Optional challenge
Enhance the solution with one additional validation case or small quality improvement while keeping the change targeted.
