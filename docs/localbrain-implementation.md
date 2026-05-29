# Local Inference Layer — LocalBrain

## Overview

The `localbrain` module loads a Gemma-compatible local LLM and runs inference on-device to convert user commands into structured `ToolCall` JSON.

## Architecture

```
┌─────────────────────────────────────────┐
│            LocalBrain.kt                  │
│  (load, infer, parse)                   │
└─────────────────────────────────────────┘
                   │
    ┌──────────────┼──────────────┐
    ▼              ▼              ▼
┌────────┐   ┌──────────┐   ┌──────────┐
│ ONNX   │   │ Prompt   │   │ JSON     │
│ Runtime│   │ Builder  │   │ Parser   │
│ Loader │   │ (Stub)   │   │          │
└────────┘   └──────────┘   └──────────┘
```

## Model Loading

- Uses **ONNX Runtime** with optional **NNAPI** acceleration.
- Falls back to CPU if NNAPI is unavailable.
- Model path is passed at runtime from `ModelManager`.

## Prompt Builder (Stub)

The public repo includes a `PromptBuilder` interface and a minimal stub implementation. The production app uses proprietary task-specific prompt templates.

```kotlin
interface PromptBuilder {
    fun build(command: String, context: String = ""): String
    fun buildChatResponse(command: String): String
}
```

## Inference

The public demo includes:
- `loadModel(path)` — initializes ONNX Runtime session
- `unloadModel()` — releases resources
- `runInference(prompt)` — **STUB**: returns a mock `ToolCall`
- `parseToolCall(output)` — extracts JSON from model output

## Production Exclusions

The following are intentionally not in the public repo:
- Tokenizer integration (SentencePiece / TikToken)
- KV-cache management for efficient generation
- Structured output parsing with retry logic
- Memory context injection into prompts
- Advanced prompt templates for each tool category

## Model Format

- **Recommended**: Gemma 2B IT quantized to ONNX or TFLite
- **Alternative**: Any ONNX-compatible small LLM
- **Path**: `models/gemma-local/` (pushed via ADB)

## Notes

- Until a real model is loaded, `RuleBasedParser` handles all commands offline.
- The rule-based parser supports 30+ command patterns.
- Model files are excluded from Git by `.gitignore`.
