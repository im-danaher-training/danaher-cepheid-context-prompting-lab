# danaher-cepheid-context-prompting-lab

## Objective
This lab is built for the Danaher/Cepheid GitHub Copilot workshop and is optimized for IntelliJ IDEA.

## Duration
40-45 minutes

## IntelliJ-first workflow
Participants should use IntelliJ IDEA throughout the exercise.

1. Open the repository in IntelliJ using File > Open.
2. Allow Maven import when IntelliJ prompts.
3. Use the Maven tool window for clean, 	est, and package tasks.
4. Run tests directly from the editor using the green triangle or Ctrl+Shift+F10.
5. Use GitHub Copilot Chat with the active file and project context loaded.
6. Review AI-generated changes before accepting them.

## Prerequisites
- IntelliJ IDEA
- JDK 17+
- Maven 3.9+
- GitHub Copilot and GitHub Copilot Chat
- Git

## Scenario
This repository uses fictional diagnostic and healthcare-technology scenarios inspired by Cepheid workflows. All data is sample-only and contains no real patient or production information.

## Starting point
The project is intentionally compact but realistic. The participant uses Copilot to explore, diagnose, fix, or improve the code while maintaining a focused learning objective.

## Hands-on tasks
1. Open the project in IntelliJ and inspect the structure.
2. Ask Copilot to explain the architecture or issue in context.
3. Apply the requested code change or refactor.
4. Add or improve the relevant tests.
5. Run validation and confirm the expected behavior.

## Validation
Run the following from the IntelliJ terminal:

`ash
mvn test
`

## Expected result
The repository compiles, tests pass, and the participant completes the intended lab outcome.

## Troubleshooting
- If Maven import fails, choose Reload All Maven Projects in IntelliJ.
- If Java version errors appear, set the JDK in Project Structure.
- If Copilot suggestions feel generic, open the exact file and ask a more direct project-aware question.

## Optional challenge
Add one additional validation scenario or a small quality improvement that keeps the code targeted to the learning objective.
