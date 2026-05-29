# Device Action Layer

## Overview

The device action module executes safe Android actions using standard system Intents. No AccessibilityService is used here — only standard, user-visible intents.

## Architecture

```
┌─────────────────────────────────────────┐
│          PhoneControl.kt                │
│  (open browser, open URL, open app)     │
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

| Method | Risk Level | Confirmation |
|--------|------------|--------------|
| Open browser | 0 | No |
| Open URL | 1 | Yes |
| Open app | 0 | No |
| Open calendar insert | 1 | Yes |
| Open camera | 0 | No |
| Open settings | 0 | No |
| Open dialer | 1 | Yes |
| Share text | 1 | Yes |

## Error Handling

Every action returns `Result<Unit>`:
- **Success** — intent was launched.
- **Error** — app not installed, permission missing, or unexpected exception.

## Package Name Resolution

`PackageResolver.kt` maps common app names to Android package names for intent launching.

## Acceptance Criteria

- All intents launch the expected system UI.
- Missing apps show clear error messages.
- Risky actions always show confirmation before executing.
- No background or hidden actions.
- Works in airplane mode.
