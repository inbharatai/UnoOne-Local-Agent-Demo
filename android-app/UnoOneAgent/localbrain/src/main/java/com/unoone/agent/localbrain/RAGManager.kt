package com.unoone.agent.localbrain

import com.unoone.agent.core.util.Logger

/**
 * Local Memory / Context Retrieval Layer (RAG Interface).
 *
 * This is a public-facing stub. The real implementation handles:
 * - Local context retrieval from the on-device database
 * - Optional lightweight online retrieval for grounding
 * - Prompt context assembly for the local inference layer
 *
 * Replace this stub with your own retrieval and prompt-construction logic.
 */
interface ContextRetrievalLayer {
    suspend fun fetchLocalContext(query: String): String
    suspend fun fetchOnlineContext(query: String): String
    fun buildPromptWithContext(command: String, localContext: String, webContext: String): String
}

/**
 * Stub implementation of the Context Retrieval Layer.
 * Does not perform real retrieval in the public demo.
 */
object StubContextRetrievalLayer : ContextRetrievalLayer {

    override suspend fun fetchLocalContext(query: String): String {
        Logger.d("ContextRetrieval: fetchLocalContext stub called for '$query'")
        return ""
    }

    override suspend fun fetchOnlineContext(query: String): String {
        Logger.d("ContextRetrieval: fetchOnlineContext stub called for '$query'")
        // NOTE: Real implementation may perform lightweight web grounding.
        // This stub returns empty to keep the public repo offline-safe.
        return ""
    }

    override fun buildPromptWithContext(
        command: String,
        localContext: String,
        webContext: String
    ): String {
        return buildString {
            appendLine("Local AI Assistant Command:")
            if (localContext.isNotBlank()) {
                appendLine("Local Context: $localContext")
            }
            if (webContext.isNotBlank()) {
                appendLine("Web Context: $webContext")
            }
            appendLine("User: $command")
            appendLine("Respond with JSON: {\"tool\": \"...\", \"args\": {...}}")
        }
    }
}
