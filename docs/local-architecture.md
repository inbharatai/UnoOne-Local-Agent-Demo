# Local Architecture

## Overview

UnoOne is built as a collection of Gradle modules to keep boundaries clean and enable independent iteration.

```
┌─────────────────────────────────────────┐
│              app                        │
│  (Compose UI, ViewModels, Navigation)   │
└─────────────────────────────────────────┘
                   │
    ┌──────────────┼──────────────┐
    ▼              ▼              ▼
┌────────┐   ┌──────────┐   ┌──────────┐
│ voice  │   │ localbrain│   │ safetyguard│
│ (STT   │   │ (Inference│   │ (Risk     │
│  /TTS) │   │  Stub)   │   │  Classifier│
└────────┘   └──────────┘   └──────────┘
    │              │              │
    ▼              ▼              ▼
┌────────┐   ┌──────────┐   ┌──────────┐
│phonecontrol│ │agentrouter│   │accessibility│
│(Intents) │   │(Tool     │   │control      │
│           │   │ Registry)│   │(Gestures)   │
└────────┘   └──────────┘   └──────────┘
    │              │              │
    ▼              ▼              ▼
┌─────────────────────────────────────────┐
│              storage                    │
│  (Room DB: Notes, Skills, Memory,      │
│   Action Logs, Model Metadata)          │
└─────────────────────────────────────────┘
```

## Module Responsibilities

| Module | Public Role |
|--------|-------------|
| `app` | Compose UI, ViewModels, FloatingService, MainActivity, PermissionManager |
| `core` | Shared data models: `Result`, `ToolCall`, `TimelineStep`, `RiskLevel`, `Logger` |
| `voice` | STT/TTS layer: AudioRecorder, local engine wrappers, Android fallback |
| `localbrain` | Local inference layer shell: ONNX model loading, stub inference, JSON parser |
| `agentrouter` | Tool registry shell: validates tool names, routes to stub handlers |
| `safetyguard` | Permission & approval layer: 4-tier risk classification |
| `phonecontrol` | Device action layer: Intents, Calendar, Package resolution |
| `accessibilitycontrol` | Accessibility layer: Click, scroll, swipe, type, read screen |
| `memory` | Local memory layer: Preference storage, corrections, pattern matching |
| `skills` | Skills/actions interface: JSON step storage, trigger matching |
| `modelmanager` | Model detection shell: File detection, checksum verification |
| `observability` | Diagnostics shell: Latency metrics, crash logging |
| `storage` | Room database: 5 entities, 5 DAOs, migrations |

## Dependency Direction

All modules depend on `core` and `storage`. The `app` module wires everything together. No module should depend on `app`.

```
app → all feature modules
core ← all feature modules
storage ← all feature modules
```

## Public Demo vs. Production

| Aspect | Public Demo | Production |
|--------|-------------|------------|
| Local LLM | Stub interface | Proprietary inference pipeline |
| Prompts | Generic stub interface | Proprietary task-specific templates |
| Context Retrieval | Stub interface | Proprietary retrieval and grounding |
| Router | Stub handlers | Full tool implementations |
| Orchestrator | Simplified flow | Advanced orchestration logic |
