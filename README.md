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
- Understands natural language via on-device STT
- Reasons locally using small Gemma-compatible models
- Controls your phone safely through Accessibility Services
- Speaks back using on-device TTS
- Learns your preferences over time via a local memory layer

---

## Tech Stack

| Layer | Technology |
|-------|------------|
| Platform | Android 9+ (API 28+) |
| Language | Kotlin |
| UI | Jetpack Compose + Material 3 |
| Architecture | MVVM with modular Gradle modules |
| Local LLM | Gemma-compatible ONNX models (via ONNX Runtime) |
| STT | Sherpa-ONNX (offline) |
| TTS | Sherpa-ONNX / Piper (offline) |
| Database | Room (SQLite) |
| Accessibility | Android AccessibilityService |

---

## Hardware Requirements

- **OS**: Android 9 (API 28) or higher
- **RAM**: 4 GB minimum, 6 GB+ recommended for LLM inference
- **Storage**: ~500 MB for app + models
- **Tested on**: Xiaomi 14 (Android 15), Samsung Galaxy S24, Pixel 8

### Model Sizes

| Feature | Model | Approx. Size |
|---------|-------|-------------|
| STT | Sherpa-ONNX ASR | ~70 MB |
| TTS | Piper / Sherpa-ONNX TTS | ~30–60 MB |
| Wake word | Keyword spotter | ~10–30 MB |
| LLM | Gemma 2B IT (int4) | ~1–2 GB |

---

## What's Included in This Public Repo

- **Android Shell**: Compose UI, ViewModels, Navigation, Screens
- **Core Models**: Result types, Timeline steps, Tool calls, Risk levels
- **Storage Layer**: Room database with Notes, Skills, Memory, Action Logs, Model Metadata
- **Voice Layer**: STT/TTS interfaces, Audio Recorder, Sherpa-ONNX wrappers, Android fallback
- **Device Action Layer**: Phone control (intents, calendar, camera), Accessibility control (click, scroll, swipe, type)
- **Permission & Approval Layer**: Risk classifier with 4-tier safety model
- **Skills/Actions Interface**: JSON-based skill storage and trigger matching
- **Local Memory Layer**: Preference storage, corrections, pattern matching
- **Model Manager**: File detection and checksum verification shell
- **Observability**: Latency metrics and crash logging shell

## What's Intentionally Excluded

The following are **not** in this public repo because they are proprietary:

- Production prompt templates and system prompts
- Advanced model routing and orchestration logic
- Internal tool names and proprietary action handlers
- Skill-generation and skill-learning logic
- Internal security validation and audit rules
- Production RAG / context retrieval implementations
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
- Commands are parsed by the rule-based fallback
- STT falls back to Android SpeechRecognizer (requires internet)
- No TTS output (silent mode)

### Push Models for Full Offline Mode

```bash
# STT
adb push models/sherpa-asr/ /sdcard/Android/data/com.unoone.agent/files/models/sherpa-asr/

# TTS
adb push models/sherpa-tts/ /sdcard/Android/data/com.unoone.agent/files/models/sherpa-tts/

# Wake word / VAD
adb push models/vad/ /sdcard/Android/data/com.unoone.agent/files/models/vad/

# Local LLM
adb push models/gemma-local/ /sdcard/Android/data/com.unoone.agent/files/models/gemma-local/
```

See `scripts/adb-push-models/` for convenience scripts.

---

## What Works in the Public Demo

### Text Commands (Offline)
- "Create a note: buy milk tomorrow"
- "Open Chrome"
- "Open WhatsApp"
- "Check calendar"
- "Read screen"
- "Scroll down / scroll up"
- "Go back / go home"
- "Find and click Login"
- "Fill username with john@example.com"

### Voice Input (Requires Models)
- Offline STT via Sherpa-ONNX
- Offline TTS via Sherpa-ONNX Piper
- Keyword spotting for wake word
- Android SpeechRecognizer fallback (online)

---

## License

License to be confirmed. Do not assume MIT or Apache-2.0 until a `LICENSE` file is added.

---

## Security

See [SECURITY.md](SECURITY.md).

## Contributing

See [CONTRIBUTING.md](CONTRIBUTING.md).

---

## Disclaimer

This is a **public demo and architecture reference**, not the production app. Some modules contain stubs or simplified implementations. The real proprietary AI layers, prompts, and routing logic are maintained in a separate private repository.
