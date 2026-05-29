# Tool Schema Registry (Public)

This document lists the public tool schemas supported by the UnoOne demo.

## create_note

```json
{
  "tool": "create_note",
  "args": {
    "title": "string",
    "content": "string",
    "tags": ["string"]
  }
}
```

**Risk:** 0 (Direct execution)

---

## search_notes

```json
{
  "tool": "search_notes",
  "args": {
    "query": "string"
  }
}
```

**Risk:** 0

---

## speak_response

```json
{
  "tool": "speak_response",
  "args": {
    "text": "string",
    "language": "string (e.g., en, hi)"
  }
}
```

**Risk:** 0

---

## open_chrome

```json
{
  "tool": "open_chrome",
  "args": {}
}
```

**Risk:** 0

---

## open_url

```json
{
  "tool": "open_url",
  "args": {
    "url": "string (full URL with https://)"
  }
}
```

**Risk:** 1 (Confirmation required)

---

## open_app

```json
{
  "tool": "open_app",
  "args": {
    "app_name": "string"
  }
}
```

**Risk:** 0

---

## open_camera

```json
{
  "tool": "open_camera",
  "args": {}
}
```

**Risk:** 0

---

## system_control

```json
{
  "tool": "system_control",
  "args": {
    "action": "click | type | fill | scroll_down | scroll_up | swipe | long_press | go_back | go_home | open_notifications | open_recents | find_and_click",
    "target": "string",
    "value": "string (for fill)"
  }
}
```

**Risk:** 1 (Requires `SYSTEM_ALERT_WINDOW` permission)

---

## read_screen

```json
{
  "tool": "read_screen",
  "args": {}
}
```

**Risk:** 1

---

## check_calendar

```json
{
  "tool": "check_calendar",
  "args": {}
}
```

**Risk:** 0

---

## open_calendar_insert

```json
{
  "tool": "open_calendar_insert",
  "args": {
    "title": "string",
    "start_time": "string (ISO-8601)",
    "end_time": "string (ISO-8601)"
  }
}
```

**Risk:** 1

---

## create_skill

```json
{
  "tool": "create_skill",
  "args": {
    "name": "string",
    "steps": ["string"]
  }
}
```

**Risk:** 0

---

## Risk Levels

| Level | Name | Behavior |
|-------|------|----------|
| 0 | DIRECT | Execute immediately |
| 1 | CONFIRM | Show confirmation dialog |
| 2 | STRONG_CONFIRM | Show strong security dialog |
| 3 | BLOCK | Block and log |

## Note

The public `AgentRouter` contains stub handlers for these tools. Real implementations are proprietary.
