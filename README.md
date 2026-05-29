# UnoOne Agent — Public Demo

> **This public repository contains the Android shell, offline-first architecture, public technical stack, and demo interfaces for UnoOne. Proprietary orchestration, internal tools, production prompts, security logic, and private model-routing layers are intentionally excluded.**

---

## What is UnoOne?

UnoOne is an **offline-first, privacy-centric Android AI assistant** that runs entirely on your device. It understands voice commands, reads your screen, controls apps, and automates tasks — **without sending any data to the cloud**.

**Zero cloud. Zero accounts. Zero data leaves your phone.**

---

## Vision

Build a fully local AI companion that:
- Works on mid-range Android phones with no internet
- Understands natural language via on-device speech recognition
- Reasons locally using small, compatible local language models
- Controls your phone safely through standard Android APIs
- Speaks back using on-device speech synthesis
- Learns your preferences over time via a local memory layer

---

## Tech Stack

| Layer | Technology |
|-------|------------|
| Platform | Android 9+ (API 28+) |
| Language | Kotlin |
| UI | Jetpack Compose + Material 3 |
| Architecture | MVVM with modular Gradle modules |
| Local Inference | ONNX-compatible models (via ONNX Runtime) |
| Speech Recognition | Offline local engine (via ONNX Runtime) |
| Speech Synthesis | Offline local engine (via ONNX Runtime) |
| Database | Room (SQLite) |
| Accessibility | Android AccessibilityService |

---

## What's Included

- **Android Shell**: Compose UI, ViewModels, Navigation, Screens
- **Core Models**: Result types, Timeline steps, Tool calls, Risk levels
- **Storage Layer**: Room database for notes, skills, memory, action logs, and model metadata
- **Voice Layer**: STT/TTS interfaces, Audio Recorder, offline engine wrappers, Android fallback
- **Device Action Layer**: Phone control (intents, calendar, camera), Accessibility control
- **Permission & Approval Layer**: Risk classifier with tiered safety model
- **Skills/Actions Interface**: JSON-based skill storage and trigger matching
- **Local Memory Layer**: Preference storage, corrections, pattern matching
- **Model Manager**: File detection shell
- **Observability**: Latency metrics and crash logging shell

## What's Intentionally Excluded

- Production prompt templates and system prompts
- Advanced model routing and orchestration logic
- Internal tool names and proprietary action handlers
- Skill-generation and skill-learning logic
- Internal security validation and audit rules
- Production context retrieval implementations
- Private datasets, evaluation traces, and test logs
- Any commit history from the private repository

---

## Project Structure

```
android-app/
  app/                  — Main app module (UI, orchestrator shell)
  core/                 — Shared models and utilities
  storage/              — Room database (entities, DAOs)
  voice/                — STT/TTS layer
  localbrain/           — Local inference layer (stub)
  agentrouter/          — Tool routing layer (stub)
  safetyguard/          — Permission & approval layer
  phonecontrol/         — Device action layer
  accessibilitycontrol/ — Accessibility service layer
  memory/               — Local memory layer
  skills/               — Skills/actions interface
  modelmanager/         — Model detection shell
  observability/        — Diagnostics shell
docs/                   — Public architecture documentation
models/                 — Placeholder directories for local models
scripts/                — ADB push and checksum helpers
```

---

## Getting Started

### Prerequisites

- Android Studio (latest stable)
- Android SDK API 34
- JDK 17
- ADB (for pushing models)

### Build

1. Open `android-app/UnoOneAgent` in Android Studio.
2. Sync Gradle.
3. Build → Make Project.

### Run Without Models

The app compiles and runs without any model files. In this mode:
- Commands are parsed by a rule-based fallback
- STT falls back to Android SpeechRecognizer (requires internet)
- No TTS output (silent mode)

### Push Models for Full Offline Mode

Place your local ONNX-compatible models in the `models/` directory and push to device storage via ADB. Model paths are configurable at runtime.

---

## License

Apache-2.0

---

## Security

See [SECURITY.md](SECURITY.md).

## Contributing

See [CONTRIBUTING.md](CONTRIBUTING.md).

---

## Disclaimer

This is a **public demo and architecture reference**, not the production app. Some modules contain stubs or simplified implementations. The real proprietary AI layers, prompts, and routing logic are maintained in a separate private repository.
