package com.unoone.agent.localbrain

import ai.onnxruntime.OrtEnvironment
import ai.onnxruntime.OrtSession
import com.unoone.agent.core.model.Result
import com.unoone.agent.core.model.ToolCall
import com.unoone.agent.core.util.Logger
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

/**
 * Local Inference Layer (Demo Stub).
 *
 * Demonstrates the architecture for loading a local ONNX-compatible model
 * and running on-device inference. The public repo includes the shell only;
 * production tokenizer integration and KV-cache management are intentionally excluded.
 */
class LocalBrain {

    private val json = Json { ignoreUnknownKeys = true }
    private var isLoaded = false

    private var env: OrtEnvironment? = null
    private var session: OrtSession? = null

    fun isModelLoaded(): Boolean = isLoaded

    fun loadModel(modelPath: String): Result<Unit> {
        return try {
            Logger.i("Loading model from $modelPath via ONNX Runtime")
            env = OrtEnvironment.getEnvironment()
            val options = OrtSession.SessionOptions()

            // Try NNAPI hardware acceleration — works on most modern Android devices
            // with dedicated NPUs (Snapdragon, Exynos, Dimensity, Tensor).
            // Gracefully falls back to CPU if NNAPI is unavailable or fails.
            try {
                options.addNnapi()
                Logger.i("NNAPI hardware acceleration enabled")
            } catch (e: Exception) {
                Logger.w("NNAPI not available on this device, using CPU inference: ${e.message}")
            }

            session = env?.createSession(modelPath, options)
            isLoaded = true
            Result.Success(Unit)
        } catch (e: Exception) {
            Logger.e("Failed to load model", e)
            Result.Error("Model load failed: ${e.message}", e)
        }
    }

    fun unloadModel() {
        Logger.i("Unloading local LLM")
        session?.close()
        env?.close()
        session = null
        env = null
        isLoaded = false
    }

    /**
     * Runs inference using the loaded ONNX model.
     *
     * NOTE: This is a public demo stub. The production implementation includes:
     * - A full tokenizer compatible with your target model format
     * - KV-cache management for efficient generation
     * - Structured output parsing with retry logic
     * - Memory context injection
     *
     * Replace this stub with your production inference pipeline.
     */
    fun runInference(prompt: String): Result<ToolCall> {
        if (!isLoaded || session == null || env == null) {
            return Result.Error("Local model not loaded")
        }

        return try {
            Logger.d("Running inference for: $prompt")

            // STUB: In the production app, this calls the real ONNX session
            // after tokenizing the prompt and managing the KV cache.
            val mockJsonOutput = "{\"tool\": \"create_note\", \"args\": {\"title\": \"Demo Note\", \"content\": \"$prompt\"}}"
            parseToolCall(mockJsonOutput)
        } catch (e: Exception) {
            Logger.e("Inference failed", e)
            Result.Error("Inference failed: ${e.message}")
        }
    }

    fun parseToolCall(output: String): Result<ToolCall> {
        return try {
            val start = output.indexOf('{')
            val end = output.lastIndexOf('}')
            if (start == -1 || end == -1) return Result.Error("No valid JSON in model output")

            val jsonStr = output.substring(start, end + 1)
            val element = json.parseToJsonElement(jsonStr)
            val obj = element.jsonObject
            val tool = obj["tool"]?.jsonPrimitive?.content ?: return Result.Error("Missing 'tool' field")
            val args = obj["args"]?.jsonObject ?: JsonObject(emptyMap())
            Result.Success(ToolCall(tool, args))
        } catch (e: Exception) {
            Result.Error("Failed to parse tool call: ${e.message}")
        }
    }
}
