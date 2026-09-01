import { readFileSync, existsSync, writeFileSync } from "node:fs";
import { fileURLToPath } from "node:url";
import path from "node:path";

const __dirname = path.dirname(fileURLToPath(import.meta.url));
const REPO_ROOT = path.resolve(__dirname, "..", "..");
const README_PATH = path.join(REPO_ROOT, "README.md");
const STATE_PATH = path.join(__dirname, "..", "plan-state.json");

const SECTION_HEADING = "## Hands-on Tasks";
const VALID_STATUSES = ["pending", "in-progress", "done"];

/**
 * Parses the numbered "Hands-on Tasks" list out of the lab README so the plan
 * shown to Copilot always matches the source of truth in the repo.
 */
export function parsePlanSteps() {
    const readme = readFileSync(README_PATH, "utf8");
    const lines = readme.split(/\r?\n/);

    const startIndex = lines.findIndex((line) => line.trim() === SECTION_HEADING);
    if (startIndex === -1) {
        throw new Error(`Could not find "${SECTION_HEADING}" section in README.md`);
    }

    const steps = [];
    for (let i = startIndex + 1; i < lines.length; i++) {
        const line = lines[i];
        if (/^##\s+/.test(line)) {
            break;
        }
        const match = line.match(/^(\d+)\.\s+(.*)$/);
        if (match) {
            steps.push({ id: Number(match[1]), title: match[2].trim() });
        }
    }

    if (steps.length === 0) {
        throw new Error(`No numbered steps found under "${SECTION_HEADING}" in README.md`);
    }

    return steps;
}

function loadState() {
    if (!existsSync(STATE_PATH)) {
        return {};
    }
    try {
        return JSON.parse(readFileSync(STATE_PATH, "utf8"));
    } catch {
        return {};
    }
}

function saveState(state) {
    writeFileSync(STATE_PATH, `${JSON.stringify(state, null, 2)}\n`, "utf8");
}

/** Returns the full plan (README steps merged with any saved progress). */
export function getPlan() {
    const steps = parsePlanSteps();
    const state = loadState();
    return steps.map((step) => ({
        ...step,
        status: state[step.id]?.status ?? "pending",
        note: state[step.id]?.note ?? null,
    }));
}

/** Updates the status/note of a single plan step and persists it. */
export function updatePlanStep(id, status, note) {
    if (!VALID_STATUSES.includes(status)) {
        throw new Error(`Invalid status "${status}". Must be one of: ${VALID_STATUSES.join(", ")}`);
    }
    const steps = parsePlanSteps();
    const step = steps.find((s) => s.id === id);
    if (!step) {
        throw new Error(`No plan step with id ${id}. Valid ids: ${steps.map((s) => s.id).join(", ")}`);
    }

    const state = loadState();
    state[id] = { status, note: note ?? state[id]?.note ?? null };
    saveState(state);

    return { ...step, status, note: state[id].note };
}

export { VALID_STATUSES };
