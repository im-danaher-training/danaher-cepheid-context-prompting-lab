#!/usr/bin/env node
import { McpServer } from "@modelcontextprotocol/sdk/server/mcp.js";
import { StdioServerTransport } from "@modelcontextprotocol/sdk/server/stdio.js";
import { z } from "zod";
import { getPlan, updatePlanStep, VALID_STATUSES } from "./planStore.js";

const server = new McpServer({
    name: "plan-view-mcp-server",
    version: "1.0.0",
});

server.registerTool(
    "view_plan",
    {
        title: "View lab plan",
        description:
            "Returns the lab's Hands-on Tasks plan (parsed from README.md) as a numbered list of " +
            "steps, each with its current status (pending, in-progress, done) and any note.",
        inputSchema: {},
    },
    async () => {
        const plan = getPlan();
        return {
            content: [{ type: "text", text: JSON.stringify({ plan }, null, 2) }],
        };
    }
);

server.registerTool(
    "update_plan_step",
    {
        title: "Update lab plan step",
        description:
            "Marks a Hands-on Tasks step as pending, in-progress, or done, optionally attaching a " +
            "short note. Progress is persisted to mcp-server/plan-state.json.",
        inputSchema: {
            id: z.number().int().positive().describe("The step number from view_plan, e.g. 1"),
            status: z.enum(VALID_STATUSES).describe("New status for the step"),
            note: z.string().optional().describe("Optional short note about progress or blockers"),
        },
    },
    async ({ id, status, note }) => {
        const step = updatePlanStep(id, status, note);
        return {
            content: [{ type: "text", text: JSON.stringify({ step }, null, 2) }],
        };
    }
);

async function main() {
    const transport = new StdioServerTransport();
    await server.connect(transport);
}

main().catch((error) => {
    console.error("plan-view-mcp-server failed to start:", error);
    process.exit(1);
});
