# Voice Layer — STT/TTS

## Overview

The voice module handles all audio I/O: microphone recording, voice activity detection, speech-to-text (STT), and text-to-speech (TTS).

```
┌─────────────────────────────────────────┐
│            VoiceModule.kt               │
│  (public API — start, stop, speak)      │
└─────────────────────────────────────────┘
                   │
    ┌──────────────┼──────────────┐
    ▼              ▼              ▼
┌────────┐   ┌──────────┐   ┌──────────┐
│ Audio  │   │ Local    │   │ Local    │
│Record  │   │ STT      │   │ TTS      │
│(PCM)   │   │ Engine   │   │ Engine   │
└────────┘   └──────────┘   └──────────┘
```

## Technology

| Component | Approach |
|-----------|----------|
| STT | Offline local engine (via ONNX Runtime) |
| TTS | Offline local engine (via ONNX Runtime) |
| Fallback STT | Android SpeechRecognizer (requires internet) |
| Audio Record | Standard Android AudioRecord (PCM) |

## Model Files

Voice models are loaded from local device storage. They are excluded from Git by `.gitignore` and pushed via ADB.

## Public Demo Status

The public repo includes:
- Audio recorder implementation
- STT engine wrapper (reflection-safe loading)
- TTS engine wrapper (reflection-safe loading)
- Android STT fallback
- Native audio track player
- Wake word detection shell

The real JNI calls are behind reflection to allow compilation without proprietary AARs. Direct calls can be enabled when dependencies and models are added.

## Notes

- All voice processing happens on-device.
- No audio data leaves the phone.
- The fallback STT (Android SpeechRecognizer) may contact Google servers if used.
