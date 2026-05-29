# Local Inference Layer

## Overview

The local inference module loads a small, compatible local language model and runs inference on-device to convert user commands into structured tool calls.

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

- Uses ONNX Runtime with optional hardware acceleration.
- Falls back to CPU if acceleration is unavailable.
- Model path is passed at runtime from the model manager.

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
- `runInference(prompt)` — **STUB**: returns a mock tool call
- `parseToolCall(output)` — extracts JSON from model output

## Production Exclusions

The following are intentionally not in the public repo:
- Tokenizer integration
- KV-cache management for efficient generation
- Structured output parsing with retry logic
- Memory context injection into prompts
- Advanced prompt templates for each tool category

## Notes

- Until a real model is loaded, a rule-based fallback handles commands offline.
- Model files are excluded from Git by `.gitignore`.
