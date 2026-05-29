# Device Action Layer — PhoneControl

## Overview

The `phonecontrol` module executes safe Android actions using system `Intents`. No AccessibilityService is used here — only standard, user-visible intents.

## Architecture

```
┌─────────────────────────────────────────┐
│          PhoneControl.kt                │
│  (openChrome, openUrl, openApp, etc.)   │
└─────────────────────────────────────────┘
                   │
    ┌──────────────┼──────────────┐
    ▼              ▼              ▼
┌────────┐   ┌──────────┐   ┌──────────┐
│ Intent │   │ Package  │   │ Calendar │
│ACTION_ │   │ Manager  │   │ Contract │
│MAIN    │   │          │   │ Intent   │
└────────┘   └──────────┘   └──────────┘
```

## Implemented Actions

| Method | Intent | Risk Level | Confirmation |
|--------|--------|------------|--------------|
| `openChrome()` | `ACTION_MAIN` + `CATEGORY_LAUNCHER` | 0 | No |
| `openUrl(url)` | `ACTION_VIEW` + URI | 1 | Yes |
| `openApp(pkg)` | `getLaunchIntentForPackage` | 0 | No |
| `openCalendarInsert(...)` | `ACTION_INSERT` + `Events.CONTENT_URI` | 1 | Yes |
| `openCamera()` | `ACTION_IMAGE_CAPTURE` | 0 | No |
| `openSettings()` | `ACTION_SETTINGS` | 0 | No |
| `openDialer(number?)` | `ACTION_DIAL` | 1 | Yes |
| `shareText(text)` | `ACTION_SEND` + `text/plain` | 1 | Yes |

## Error Handling

Every action returns `Result<Unit>`:
- **Success** — intent was launched.
- **Error** — app not installed, permission missing, or unexpected exception.

## Package Name Resolution

`PackageResolver.kt` maps common app names to package names:
- WhatsApp → `com.whatsapp`
- Gmail → `com.google.android.gmail`
- Calendar → `com.google.android.calendar`
- Chrome → `com.android.chrome`
- YouTube → `com.google.android.youtube`

## Testing

- Speak "Open Chrome" → Chrome opens.
- Speak "Open WhatsApp" → WhatsApp opens if installed.
- Speak "Open google dot com" → Confirmation dialog → Chrome opens URL.

## Acceptance Criteria

- All intents launch the expected system UI.
- Missing apps show clear error messages.
- Risk 1 actions always show confirmation before executing.
- No background or hidden actions.
- Works in airplane mode.
