# danaher-cepheid-context-prompting-lab

## Objective
This lab is built for the Danaher/Cepheid GitHub Copilot workshop and is optimized for IntelliJ IDEA.

## Duration
40-45 minutes

## IntelliJ-first workflow
1. Open the repository root in IntelliJ IDEA.
2. Let Maven import the project automatically.
3. Use the Maven tool window for clean, 	est, and package actions.
4. Run tests directly from the source editor using the green triangle or Ctrl+Shift+F10.
5. Use GitHub Copilot Chat with the active file and repository context loaded.
6. Review the AI-generated diff before accepting it.

## Prerequisites
- IntelliJ IDEA
- JDK 17+
- Maven 3.9+
- GitHub Copilot and GitHub Copilot Chat
- Git

## Scenario
This repo contains a fictional diagnostic workflow inspired by Cepheid instrument and order scenarios. All data is sample-only and contains no real patient or production information.

## Starting point
The project is intentionally small and realistic, with a focused learning objective and a manageable amount of setup work.

## Hands-on tasks
1. Explore the repository in IntelliJ.
2. Ask Copilot to explain the application or issue in context.
3. Apply the requested improvement or fix.
4. Add or improve the relevant tests.
5. Run validation and confirm the expected behavior.

## Validation
`ash
mvn test
`

## Troubleshooting
- If Maven import fails, select Reload All Maven Projects.
- If Java is not recognized, set JDK 17 in Project Structure.
- If Copilot output is generic, open the exact file and ask a more precise prompt.

## Optional challenge
Add one additional validation case or a small targeted quality improvement without broadening the scope.
