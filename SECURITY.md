# Security Policy

## Scope

This public repository contains the **Android shell, architecture reference, and demo interfaces** for UnoOne. It does **not** contain the production application.

## What Is NOT in This Repo

The following are intentionally excluded to protect proprietary and sensitive components:

- Production API keys, tokens, and credentials
- Supabase, Firebase, or any cloud service configurations
- Production prompt templates and system prompts
- Internal tool names and proprietary action handlers
- Advanced security validation and audit rules
- Private datasets, evaluation traces, and test logs
- Real user data or business strategy documents
- Original private Git history

## Reporting Security Issues

If you discover a security vulnerability in the public demo:

1. **Do not** open a public issue.
2. Email the maintainers directly with details.
3. Allow reasonable time for response before any public disclosure.

## Demo Safety

- No network requests are made by the public demo unless you explicitly enable the Android STT fallback (which uses the system SpeechRecognizer and may contact Google).
- The app requests standard Android permissions (microphone, calendar, camera, accessibility). Review these before installing.
- Model files are loaded from local device storage only.

## Build Safety

- Always review `.env.example` and never commit real secrets.
- Do not push local model binaries to Git. They are excluded by `.gitignore`.
- Keep the private repository separate. Do not merge histories.
