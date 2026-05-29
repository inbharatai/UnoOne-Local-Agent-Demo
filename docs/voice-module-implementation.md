# Voice Module — STT/TTS Layer

## Overview

The voice module handles all audio I/O: microphone recording, Voice Activity Detection (VAD), Speech-to-Text (STT), and Text-to-Speech (TTS).

```
┌─────────────────────────────────────────┐
│            VoiceModule.kt               │
│  (public API — start, stop, speak)      │
└─────────────────────────────────────────┘
                   │
    ┌──────────────┼──────────────┐
    ▼              ▼              ▼
┌────────┐   ┌──────────┐   ┌──────────┐
│ Audio  │   │ Sherpa   │   │ Sherpa   │
│Record  │   │ STT      │   │ TTS      │
│(PCM)   │   │(ONNX)    │   │(ONNX)    │
└────────┘   └──────────┘   └──────────┘
```

## Technology

| Component | Library | Note |
|-----------|---------|------|
| STT | Sherpa-ONNX | Offline, supports English + Indian languages |
| TTS | Sherpa-ONNX / Piper | Offline, supports English + Indian languages |
| Fallback STT | Android SpeechRecognizer | Requires internet |
| Audio Record | AudioRecord (PCM 16-bit) | 16 kHz mono |

## Dependencies

```kotlin
// In voice/build.gradle.kts
implementation("com.github.k2-fsa:sherpa-onnx-android:1.10.0")
```

## Model Files

| Model | Size | Push Command |
|-------|------|-------------|
| Sherpa-ONNX ASR | ~70 MB | `adb push models/sherpa-asr/ ...` |
| Sherpa-ONNX TTS | ~30–60 MB | `adb push models/sherpa-tts/ ...` |
| VAD / Wake word | ~10–30 MB | `adb push models/vad/ ...` |

## Public Demo Status

The public repo includes:
- `AudioRecorder.kt` — full implementation
- `SherpaSttEngine.kt` — wrapper with reflection-safe loading
- `SherpaTtsEngine.kt` — wrapper with reflection-safe loading
- `AndroidSttEngine.kt` — fallback implementation
- `TtsPlayer.kt` — native audio track player
- `KeywordSpotter.kt` — wake word detection shell

The real Sherpa-ONNX JNI calls are behind reflection to allow compilation without the AAR. Uncomment the direct calls when you add the dependency and models.

## Notes

- All voice processing happens on-device.
- No audio data leaves the phone.
- The fallback STT (Android SpeechRecognizer) may contact Google servers if used.
