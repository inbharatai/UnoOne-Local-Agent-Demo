package com.unoone.agent.localbrain

/**
 * Prompt Builder Interface for the Local Inference Layer.
 *
 * The real implementation constructs task-specific prompts for the on-device LLM.
 * This public repo includes only the interface and a minimal stub to demonstrate
 * the architecture without exposing proprietary prompt templates.
 */
interface PromptBuilder {
    fun build(command: String, context: String = ""): String
    fun buildChatResponse(command: String): String
}

/**
 * Stub implementation. Replace with your own prompt templates.
 */
object StubPromptBuilder : PromptBuilder {

    private val availableTools = listOf(
        "create_note" to "Create a note with content",
        "draft_email" to "Draft an email with to, subject, body",
        "send_whatsapp" to "Send a WhatsApp message with number, message",
        "check_calendar" to "Check today's calendar events",
        "open_calendar_insert" to "Open calendar event creator with title",
        "open_chrome" to "Open Chrome browser",
        "open_url" to "Open a URL",
        "open_app" to "Open an app by name",
        "open_camera" to "Open the camera app",
        "system_control" to "Control the device: click, type, fill, scroll, swipe, go_back, go_home, find_and_click, read_screen",
        "read_screen" to "Read text visible on the current screen",
        "create_skill" to "Save a sequence of actions as a reusable skill"
    )

    override fun build(command: String, context: String): String {
        return buildString {
            appendLine("You are a local-first AI phone agent.")
            appendLine("Available tools:")
            availableTools.forEach { (name, desc) -> appendLine("  - $name: $desc") }
            appendLine("Respond ONLY with a JSON object in this exact format:")
            appendLine("""{"tool": "tool_name", "args": {"key": "value"}}""")
            if (context.isNotBlank()) {
                appendLine("User context/preferences: $context")
            }
            appendLine("User command: $command")
            appendLine("JSON:")
        }
    }

    override fun buildChatResponse(command: String): String {
        return """
            You are a helpful local AI assistant.
            User said: $command
            Respond briefly and clearly. No JSON needed.
        """.trimIndent()
    }
}
