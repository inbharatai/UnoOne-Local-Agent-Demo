# UnoOne Public Demo — Build Status

**Last updated:** 2026-05-29
**Build:** `1.0.0-demo` | **Target:** Android 14 (API 34) | **Min:** Android 9 (API 28)

---

## Module Status

| Module | Status | Notes |
|--------|--------|-------|
| `app` | Included | Compose UI, ViewModels, FloatingService, Permissions |
| `core` | Included | Result, ToolCall, TimelineStep, RiskLevel, Logger |
| `storage` | Included | Room DB with 5 entities, 5 DAOs |
| `modelmanager` | Included | Model detection shell |
| `localbrain` | **Stub** | ONNX shell + rule-based fallback only |
| `voice` | Included | Sherpa-ONNX wrappers, Android fallback |
| `agentrouter` | **Stub** | Tool registry skeleton |
| `safetyguard` | Included | 4-tier risk classifier |
| `phonecontrol` | Included | PhoneControl, CalendarControl, PackageResolver |
| `memory` | Included | Keyword context matching, preferences |
| `skills` | Included | JSON step storage, trigger matching |
| `observability` | Included | Latency metrics shell |
| `accessibilitycontrol` | Included | Click, type, fill, scroll, swipe, read screen |

## Known Limitations of Public Demo

- Local LLM inference is stubbed — real tokenizer and KV-cache are proprietary
- RAG / context retrieval is stubbed — online retrieval logic is excluded
- Prompt builder is a generic stub — production prompts are private
- AgentRouter handlers are stubs — real tool implementations are private
- Some advanced orchestration features (retry logic, multi-step planning) are simplified

---

> This public demo is intended to show architecture and UI flow. For the full offline AI experience, proprietary layers are required.
