# Plan View MCP Server

A small custom [Model Context Protocol (MCP)](https://modelcontextprotocol.io/) server built for this
lab. It gives GitHub Copilot Chat a **live, structured view of the lab's "Hands-on Tasks" plan**
(parsed straight from the repo's [`README.md`](../README.md)) and lets Copilot mark steps as you
complete them — instead of Copilot only ever seeing the plan as unstructured prose.

This is the fourth Copilot customization pattern in the lab, alongside custom agents
(`.github/agents/`), skills (`.github/skills/`), and `AGENTS.md`. Where agents and skills shape
*how* Copilot responds, an MCP server gives Copilot new *tools* it can call.

## What it does

The server exposes two tools over stdio:

| Tool | Description |
|---|---|
| `view_plan` | Returns every numbered step from the README's `## Hands-on Tasks` section, each with an `id`, `title`, `status` (`pending` / `in-progress` / `done`), and optional `note`. |
| `update_plan_step` | Updates the `status` (and optional `note`) of one step by `id`. Progress is persisted to `mcp-server/plan-state.json` (git-ignored, local to your machine). |

The plan steps always come from the README, so the tool never drifts out of sync with the actual
lab instructions — only the status/notes are stored separately.

## Project layout

```
mcp-server/
├── package.json        # dependencies + start script
├── src/
│   ├── index.js         # MCP server: registers view_plan and update_plan_step tools
│   └── planStore.js     # parses README.md and reads/writes plan-state.json
└── plan-state.json       # generated at runtime, git-ignored
```

## Prerequisites

- Node.js 18+
- npm

## Setup

```bash
cd mcp-server
npm install
```

## Running it standalone (for testing)

The server speaks MCP over stdio, so it isn't meant to be run interactively — but you can smoke-test
that it starts:

```bash
npm start
```

It will sit and wait for MCP JSON-RPC messages on stdin; press `Ctrl+C` to stop it. Use an MCP
client (or the IDE integration below) to actually call its tools.

## Registering it with GitHub Copilot

### VS Code

Add an entry to your workspace `.vscode/mcp.json` (create the file if it doesn't exist):

```json
{
  "servers": {
    "plan-view": {
      "command": "node",
      "args": ["${workspaceFolder}/mcp-server/src/index.js"]
    }
  }
}
```

Reload the window, then open Copilot Chat, switch to **Agent** mode, and enable the `plan-view`
server from the tools picker.

### IntelliJ IDEA

1. Open **Settings/Preferences > GitHub Copilot > MCP** (or the equivalent MCP servers panel for
   your Copilot plugin version).
2. Add a new server with:
   - **Command**: `node`
   - **Args**: `mcp-server/src/index.js` (path relative to the repo root, or an absolute path)
3. Save and enable the server, then ask Copilot Chat something like:
   ```
   Use the plan-view MCP server to show me the lab plan and mark step 1 as done.
   ```

## Example prompts once connected

```
Show me the current lab plan using the plan-view MCP server.
```

```
Mark step 2 of the plan as in-progress with the note "starting the migration prompt".
```

## Extending it

This server intentionally does one thing well: viewing and updating this lab's plan. If you want to
practice building your own MCP server, try adding a tool that:
- Resets all steps back to `pending`.
- Returns only the steps that are still `pending`.
- Reads plan steps from a different section of the README (e.g. the "Copilot Customization Guide"
  sub-steps).
