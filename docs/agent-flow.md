# Agent Flow

## High-Level Pipeline

```
User speaks
    │
    ▼
┌──────────────────────────────────────┐
│ 1. LISTENING                         │
│    • Mic permission check             │
│    • AudioRecord starts               │
│    • VAD detects voice start          │
└──────────────────────────────────────┘
    │
    ▼
┌──────────────────────────────────────┐
│ 2. TRANSCRIBING                      │
│    • PCM buffer → STT Layer          │
│    • Measure STT latency             │
└──────────────────────────────────────┘
    │
    ▼
┌──────────────────────────────────────┐
│ 3. UNDERSTANDING                     │
│    • Text → Local Inference Layer    │
│    • Optional memory context         │
│    • Parse structured tool call      │
└──────────────────────────────────────┘
    │
    ▼
┌──────────────────────────────────────┐
│ 4. TOOL SELECTED                     │
│    • Validate tool exists            │
│    • Validate required args          │
└──────────────────────────────────────┘
    │
    ▼
┌──────────────────────────────────────┐
│ 5. SAFETY CHECK                      │
│    • Risk classifier runs            │
│    • Low risk: direct execute        │
│    • Medium risk: confirmation       │
│    • High risk: strong confirmation  │
│    • Critical: block + log           │
└──────────────────────────────────────┘
    │
    ▼ (if approved)
┌──────────────────────────────────────┐
│ 6. EXECUTING                         │
│    • Invoke Android Intent or DB op  │
│    • Catch exceptions                │
└──────────────────────────────────────┘
    │
    ▼
┌──────────────────────────────────────┐
│ 7. VERIFYING                         │
│    • Check action result             │
│    • Store ActionLog entry           │
└──────────────────────────────────────┘
    │
    ▼
┌──────────────────────────────────────┐
│ 8. SPEAKING RESPONSE                 │
│    • Format confirmation text        │
│    • Run TTS                         │
└──────────────────────────────────────┘
    │
    ▼
┌──────────────────────────────────────┐
│ 9. DONE / FAILED                     │
│    • Update timeline to final state  │
│    • Show user-readable reason       │
└──────────────────────────────────────┘
```

## UI Timeline States

| # | State Label        | Color / Icon        |
|---|--------------------|---------------------|
| 1 | Listening          | Red pulse, mic      |
| 2 | Transcribing       | Yellow, waveform    |
| 3 | Understanding      | Blue, brain icon    |
| 4 | Tool Selected      | Purple, wrench      |
| 5 | Safety Check       | Orange, shield      |
| 6 | Executing          | Cyan, gear spin     |
| 7 | Verifying          | Teal, checkmark     |
| 8 | Speaking Response  | Green, speaker      |
| 9 | Done               | Green check         |
| - | Failed             | Red X, retry button |

## Implementation Note

The public demo includes the timeline UI and orchestrator shell. Advanced retry logic, multi-step planning, and proprietary routing optimizations are intentionally excluded.
