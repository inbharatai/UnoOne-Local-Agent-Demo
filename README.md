<div align="center">

# 🤖 UnoOne Agent — Public Demo

### **Your Phone. Your Intelligence. Your Privacy.**

> A fully offline Android AI companion that lives on your device —  
> understanding voice, reading screens, controlling apps, and automating tasks.  
> **Zero cloud. Zero accounts. Zero data leaves your phone.**

<p align="center">
  <img src="https://img.shields.io/badge/Platform-Android-green?style=for-the-badge&logo=android" alt="Android">
  <img src="https://img.shields.io/badge/Language-Kotlin-purple?style=for-the-badge&logo=kotlin" alt="Kotlin">
  <img src="https://img.shields.io/badge/UI-Compose%20%2B%20Material%203-blue?style=for-the-badge&logo=jetpackcompose" alt="Compose">
  <img src="https://img.shields.io/badge/Privacy-100%25%20Offline-critical?style=for-the-badge&logo=privacyguides" alt="Offline">
  <img src="https://img.shields.io/badge/License-Apache%202.0-blue?style=for-the-badge" alt="License">
</p>

<p align="center">
  <img src="https://img.shields.io/badge/API-28%2B%20(Android%209%2B)-success?style=flat-square" alt="API 28+">
  <img src="https://img.shields.io/badge/Modules-13-9cf?style=flat-square" alt="13 Modules">
  <img src="https://img.shields.io/badge/Architecture-MVVM-informational?style=flat-square" alt="MVVM">
  <img src="https://img.shields.io/badge/Safety-4%20Tier%20Classifier-orange?style=flat-square" alt="Safety">
  <img src="https://img.shields.io/badge/Voice-Offline%20Local%20Engine-blueviolet?style=flat-square" alt="Offline Voice">
</p>

---

</div>

## 📚 Quick Navigation

- [What is UnoOne?](#-what-is-unoone)
- [Capabilities](#-capabilities)
- [Architecture](#-architecture)
- [Tech Stack](#-tech-stack)
- [Project Structure](#-project-structure)
- [Getting Started](#-getting-started)
- [Voice Commands](#-voice-commands)
- [Permissions](#-permissions)
- [Disclaimer](#-disclaimer)

---

## ✨ What is UnoOne?

UnoOne is an **offline-first, privacy-centric Android AI assistant** that runs entirely on your device. It understands voice commands, reads your screen, controls apps, and automates tasks — **without sending any data to the cloud**.

<table>
<tr>
<td width="50%">

### 🎙️ Voice-First
Say **"UnoOne"** and speak naturally. Wake word detection, offline STT, and spoken responses — no internet needed.

</td>
<td width="50%">

### 📱 Deep Control
Tap, scroll, swipe, type, read screens — all through Android's Accessibility Service. Your phone, automated.

</td>
</tr>
<tr>
<td width="50%">

### 🧠 Local Intelligence
Rule-based parser handles command patterns instantly. ONNX-compatible LLM fallback for complex requests. Memory that learns your preferences.

</td>
<td width="50%">

### 🔒 Privacy by Design
Every byte stays on your device. No cloud APIs, no telemetry, no accounts. Your data never leaves your phone.

</td>
</tr>
</table>

---

## 🚀 Capabilities

| 🎯 Capability | ⚙️ How It Works | 📡 Offline? |
|:-------------|:----------------|:----------:|
| **Voice wake word** | Local keyword spotter ("UnoOne") | ✅ |
| **Speech-to-text** | Offline local transducer model | ✅ |
| **Command parsing** | Rule-based parser + on-device LLM fallback | ✅ |
| **Text-to-speech** | Offline local synthesis engine | ✅ |
| **Screen reading** | Accessibility tree capture + optional OCR | ✅ |
| **App control** | Accessibility gestures (tap, scroll, type, swipe) | ✅ |
| **Skill automation** | Record multi-step workflows, trigger by voice | ✅ |
| **Safety guard** | 4-tier risk classifier with confirmation dialogs | ✅ |
| **Notes & Calendar** | Android intents + ContentProvider queries | ✅ |

---

## 🏗️ Architecture

```
┌──────────────────────────────────────────────────────────────────────┐
│                            ✨ UI LAYER                                │
│                                                                      │
│   ┌──────────┐  ┌──────────┐  ┌──────────┐  ┌──────────┐ ┌────────┐│
│   │  🤖 Agent│  │  📝 Notes│  │  ⚡ Skills│  │  📋 Logs │ │ ⚙️ Set ││
│   │  Screen  │  │  Screen  │  │  Screen  │  │  Screen  │ │ tings  ││
│   └────┬─────┘  └──────────┘  └────┬─────┘  └──────────┘ └────────┘│
│        │        Jetpack Compose UI      │                            │
│   ┌────┴───────────────────────────────┴─────┐                      │
│   │  💬 FloatingAgentService (bubble overlay) │                      │
│   │  🌊 WaveformVisualizer · ✅ Confirmation│                      │
│   └─────────────────┬────────────────────────┘                      │
└──────────────────────┼───────────────────────────────────────────────┘
                       │
┌──────────────────────┼───────────────────────────────────────────────┐
│              🧠 AGENT ORCHESTRATOR (8-Step Pipeline)                  │
│                                                                      │
│   ┌──────────────────────────────────────────────────────────────┐  │
│   │  ① Skill Match → ② Parse → ③ Permission → ④ Safety          │  │
│   │  → ⑤ Confirm? → ⑥ Execute → ⑦ Verify → ⑧ Speak             │  │
│   └──────────────────────────────────────────────────────────────┘  │
└──────┬──────────┬──────────┬──────────┬──────────┬────────────────┘
       │          │          │          │          │
  ┌────┴────┐ ┌───┴───┐ ┌───┴────┐ ┌───┴───┐ ┌───┴────────┐
  │  🎙️    │ │ 🧠    │ │ 🛡️    │ │ 📱   │ │ ♿        │
  │  Voice  │ │ Local │ │ Safety │ │ Phone│ │ Access-  │
  │  Module │ │ Brain │ │ Guard  │ │ Ctrl │ │ ibility  │
  │         │ │       │ │        │ │      │ │ Control  │
  │ • STT   │ │ •Rule │ │ •4-tier│ │ •Open│ │ •Tap/Type│
  │ • TTS   │ │ parser│ │  risk  │ │ •Mail│ │ •Scroll │
  │ • KWS   │ │ •ONNX │ │  class │ │ •Chat│ │ •Swipe  │
  │ •Android│ │  LLM  │ │ •Block │ │ •Cal │ │ •Screen │
  │  fallback│ │ •Mem  │ │ •Conf. │ │ •OCR │ │ •Back   │
  └─────────┘ └───────┘ └────────┘ └──────┘ └──────────┘
       │          │          │          │          │
┌──────┴──────────┴──────────┴──────────┴──────────┴──────────────────┐
│                     💾 STORAGE LAYER (Room DB)                         │
│                                                                      │
│  ┌─────────┐  ┌─────────┐  ┌─────────┐  ┌──────────┐  ┌──────────┐ │
│  │  Notes  │  │ Skills  │  │Memories │  │ActionLogs│  │ModelMeta │ │
│  │  DAO    │  │  DAO    │  │  DAO    │  │   DAO    │  │   DAO    │ │
│  └─────────┘  └─────────┘  └─────────┘  └──────────┘  └──────────┘ │
└────────────────────────────────────────────────────────────────────────┘
```

### Module Dependency Graph

```
app ─┬─ core ──────── Result, ToolCall, TimelineStep, Logger
     ├─ storage ───── Room entities, DAOs, database
     ├─ modelmanager ─ Model folder detection, checksums
     ├─ localbrain ─── RuleBasedParser, PromptBuilder stub, LocalBrain (ONNX)
     ├─ voice ─────── AudioRecorder, LocalSttEngine, LocalTtsEngine,
     │                KeywordSpotterEngine, AndroidSttEngine, TtsPlayer,
     │                VoiceService (foreground), VoiceModule
     ├─ agentrouter ── Tool registry stub
     ├─ safetyguard ── RiskLevel classifier (DIRECT/CONFIRM/STRONG_CONFIRM/BLOCK)
     ├─ phonecontrol ─ PhoneControl, CalendarControl, OcrControl, PackageResolver
     ├─ memory ─────── MemoryModule (preferences, corrections, keyword context)
     ├─ skills ─────── SkillsModule (CRUD, trigger matching, JSON step storage)
     ├─ observability ─ Diagnostics (latency, success rates)
     └─ accessibilitycontrol ─ UnoOneAccessibilityService, AccessibilityControl
```

### 🔄 Agent Loop — 8 Steps

```
  🎤 Voice / ⌨️ Text Input
         │
         ▼
  ① 🔍 Skill Match ────── Is this a saved skill trigger? → Execute each step
         │
         ▼
  ② 🧩 Parse ──────────── RuleBasedParser → ONNX LLM fallback → ToolCall JSON
         │
         ▼
  ③ 🔑 Permission Check ── Missing runtime permissions? → Request & pause
         │
         ▼
  ④ 🛡️ Safety Classify ─── DIRECT / CONFIRM / STRONG_CONFIRM / BLOCK
         │
         ▼
  ⑤ ⚠️ Confirmation ────── CONFIRM: allow/deny dialog
         │                  STRONG_CONFIRM: type "confirm" to proceed
         │                  BLOCK: reject immediately
         ▼
  ⑥ ⚡ Execute ─────────── Dispatch to PhoneControl, AccessibilityControl, etc.
         │
         ▼
  ⑦ ✅ Verify ──────────── Log result, check success
         │
         ▼
  ⑧ 🔊 Speak / Display ── TTS for voice input, timeline update for text
```

---

## 🛠️ Tech Stack

| Layer | Technology |
|:------|:-----------|
| **Language** | Kotlin |
| **UI** | Jetpack Compose + Material 3 |
| **Architecture** | MVVM (manual DI, no Hilt/Koin) |
| **Database** | Room (SQLite) |
| **Serialization** | kotlinx-serialization |
| **Coroutines** | kotlinx-coroutines |
| **LLM Inference** | ONNX Runtime |
| **Speech** | Offline local engine (via ONNX Runtime) |
| **OCR** | Google ML Kit Text Recognition (optional) |
| **Accessibility** | Android AccessibilityService + GestureDescription |
| **Build** | AGP · Gradle · KSP |
| **Target** | Android 9 (API 28) → Android 14 (API 34) |

---

## 📂 Project Structure

```
UnoOne-Local-Agent-Demo/
├── android-app/UnoOneAgent/           # 📱 Android Studio project root
│   ├── app/                           # 🚀 Application shell
│   │   └── src/main/java/com/unoone/agent/
│   │       ├── AgentOrchestrator.kt       # 8-step agent pipeline
│   │       ├── FloatingAgentService.kt    # Floating bubble + chat overlay
│   │       ├── MainActivity.kt            # Permissions, battery optimization
│   │       ├── PermissionManager.kt       # Runtime & system permissions
│   │       ├── UnoOneApplication.kt       # App entry, orchestrator init
│   │       └── ui/
│   │           ├── screens/               # AgentScreen, NotesScreen, SkillsScreen
│   │           ├── components/            # WaveformVisualizer, ConfirmationDialog
│   │           ├── viewmodel/             # AgentViewModel, SkillsViewModel
│   │           ├── navigation/            # UnoOneNavHost, Screen
│   │           └── theme/                 # UnoOneTheme, Color, Type
│   ├── core/                          # 📦 Result, ToolCall, TimelineStep, Logger
│   ├── storage/                       # 🗄️ Room entities, DAOs, database
│   ├── modelmanager/                  # 📂 Model folder detection, checksums
│   ├── localbrain/                    # 🧠 RuleBasedParser, PromptBuilder stub, LocalBrain
│   ├── voice/                         # 🎙️ VoiceModule, VoiceService, AudioRecorder
│   ├── agentrouter/                   # 🔀 AgentRouter stub, tool registry shell
│   ├── safetyguard/                   # 🛡️ SafetyGuard, 4-tier risk classification
│   ├── phonecontrol/                  # 📱 PhoneControl, CalendarControl, OcrControl
│   ├── memory/                        # 💭 MemoryModule (keyword context matching)
│   ├── skills/                        # ⚡ SkillsModule (JSON step storage)
│   ├── observability/                 # 📊 Diagnostics (latency, success rates)
│   └── accessibilitycontrol/         # ♿ UnoOneAccessibilityService, AccessibilityControl
├── models/                            # 🧠 On-device model files (pushed via ADB)
│   ├── local-llm/                     # Local ONNX-compatible language model
│   ├── stt/                           # Speech-to-text model
│   ├── tts/                           # Text-to-speech model
│   ├── vad/                           # Voice activity / keyword spotting
│   ├── ocr/                           # Optional OCR model
│   └── punctuation/                 # Optional punctuation model
├── scripts/                           # 🔧 ADB push helpers
└── docs/                              # 📖 Architecture documentation
```

---

## 🏁 Getting Started

### Prerequisites

| Tool | Version | Purpose |
|:-----|:--------|:--------|
| Android Studio | Latest stable | IDE, build, debug |
| Android SDK | API 34 | Compile target |
| JDK | 17 | Kotlin compilation |
| ADB | Latest | Device deployment, model push |

### Build & Run

```bash
# 1. Open in Android Studio
#    File → Open → UnoOne-Local-Agent-Demo/android-app/UnoOneAgent

# 2. Wait for Gradle sync

# 3. Connect your Android device (USB Debugging enabled)

# 4. Build and install:
./gradlew assembleDebug
adb install app/build/outputs/apk/debug/app-debug.apk

# 5. On first launch, grant:
#    🎙️ Microphone, 📇 Contacts, 📅 Calendar, 📷 Camera
#    🖼️ Display over other apps (overlay)
#    ♿ Accessibility Service (for deep control)
#    🔋 Disable battery optimization
```

### Push Model Files (Required for Full Offline Mode)

Place your local ONNX-compatible models in the `models/` directory and push to device storage via ADB. Model paths are configurable at runtime.

> ⚠️ **Without model files**, the app falls back to: Android `SpeechRecognizer` for STT (requires internet), rule-based command parsing for NLU, and no TTS output.

---

## 🎤 Voice Commands

### Quick Commands (No Models Required)

| 🗣️ Command | ⚡ Action |
|:-----------|:---------|
| `"Create a note: buy milk"` | Saves note to Room DB |
| `"Open Chrome"` | Launches Chrome |
| `"Open WhatsApp"` | Launches WhatsApp |
| `"Open calendar"` | Opens calendar insert |
| `"Read screen"` | Reads all visible text via accessibility |
| `"Scroll down"` | Scrolls current app down |
| `"Go back"` | Presses back button |
| `"Go home"` | Presses home button |

### Skill Commands

| 🗣️ Command | ⚡ Action |
|:-----------|:---------|
| `"Teach you a skill called Morning to open Chrome then read screen"` | Creates a reusable skill |
| `"Morning"` | Triggers the saved skill's steps |

### Deep Control (Accessibility Required)

| 🗣️ Command | ⚡ Action |
|:-----------|:---------|
| `"Find and click Login"` | Scrolls to find "Login" text and taps it |
| `"Fill username with john@example.com"` | Types into the field with "username" hint |
| `"Swipe left"` | Performs left swipe gesture |
| `"Open notifications"` | Opens notification shade |

---

## 📋 Module Deep Dive

### 🎙️ Voice Pipeline

```
AudioRecorder (16kHz PCM, continuous)
       │
       ▼
KeywordSpotterEngine ("UnoOne" wake word)
       │ detected
       ▼
VAD Silence Detection (energy-based RMS)
       │ pause detected
       ▼
LocalSttEngine (offline transducer)
       │ or fallback
       ▼
AndroidSttEngine (Google SpeechRecognizer)
       │
       ▼
AgentOrchestrator.processCommand(text, VOICE)
       │
       ▼
LocalTtsEngine → TtsPlayer (AudioTrack)
```

### ♿ Accessibility Control

All actions go through `UnoOneAccessibilityService` which requires explicit user enablement:

| Method | What It Does |
|:-------|:------------|
| `clickNodeWithText(text)` | Finds and clicks a node by its text |
| `clickAt(x, y)` | Clicks at exact coordinates |
| `typeTextIntoFocused(text)` | Types into the currently focused input |
| `fillFieldWithText(hint, text)` | Finds editable by hint and fills it |
| `scrollDown()` / `scrollUp()` | Scrolls via gesture swipe |
| `swipe(direction)` | Swipes left / right / up / down |
| `longPress(x, y)` | Long press at coordinates |
| `goBack()` / `goHome()` | Global navigation actions |
| `openNotifications()` / `openRecents()` | System UI actions |
| `findAndClick(text)` | Scrolls to find text, then clicks it |
| `captureVisibleText()` | Reads all text from the accessibility tree |

### 🛡️ Safety Guard

Every action is classified before execution:

| ⚠️ Risk Level | 🔒 Behavior | 📋 Example |
|:-------------|:-----------|:----------|
| **DIRECT** | Execute immediately | Create note, open Chrome |
| **CONFIRM** | Show allow/deny dialog | Send message, open camera |
| **STRONG_CONFIRM** | Type "confirm" to proceed | Draft email, fill passwords |
| **BLOCK** | Reject immediately | Destructive actions |

---

## 🔐 Permissions

| Permission | Purpose | Required |
|:----------|:--------|:--------:|
| `RECORD_AUDIO` | Voice commands, wake word | ✅ |
| `READ_CONTACTS` | Messaging contact resolution | ✅ |
| `READ_CALENDAR` / `WRITE_CALENDAR` | Calendar event queries | ✅ |
| `CAMERA` | Open camera intent | ✅ |
| `POST_NOTIFICATIONS` | Foreground service notification (API 33+) | ✅ |
| `SYSTEM_ALERT_WINDOW` | Floating bubble overlay | ✅ |
| Accessibility Service | Deep app control (tap, scroll, type) | ✅ |
| `MANAGE_EXTERNAL_STORAGE` | Model file management (API 30+) | Optional |
| `REQUEST_IGNORE_BATTERY_OPTIMIZATIONS` | Prevent background service kill | Recommended |
| `FOREGROUND_SERVICE_MICROPHONE` | Background wake word detection | ✅ |
| `FOREGROUND_SERVICE_SPECIAL_USE` | Floating bubble service | ✅ |

---

## ⚠️ Disclaimer

> **This is a public demo and architecture reference**, not the production app. Some modules contain stubs or simplified implementations. The real proprietary AI layers, prompts, routing logic, and production context retrieval are maintained in a separate private repository.
>
> Proprietary orchestration, internal tools, production prompts, security logic, and private model-routing layers are intentionally excluded.

---

## 📄 License

Apache-2.0 — See [LICENSE](LICENSE) for details.

---

<div align="center">

### Built with ❤️ by [InBharatAI](https://github.com/inbharatai)

**UnoOne** — *One agent. One device. Zero compromises.*

</div>
