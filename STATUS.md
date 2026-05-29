# UnoOne Public Demo — Build Status

**Last updated:** 2026-05-29
**Build:** `1.0.0-demo` | **Target:** Android 14 (API 34) | **Min:** Android 9 (API 28)

---

## Status

All public shell modules are included. Proprietary inference, routing, and prompt layers are stubbed.

## Known Limitations

- Local LLM inference is stubbed — returns mock structured output.
- Context retrieval is stubbed — no real local or online grounding.
- Prompt builder is a generic interface — production templates are private.
- Tool routing handlers are stubs — real implementations are private.
- Advanced orchestration features are simplified.

---

> This public demo is intended to show architecture and UI flow. For the full offline AI experience, proprietary layers are required.
