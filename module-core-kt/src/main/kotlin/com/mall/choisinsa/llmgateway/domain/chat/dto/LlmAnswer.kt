package com.mall.choisinsa.llmgateway.domain.chat.dto

// LLM이 반드시 맞춰야 할 JSON 구조
data class LlmAnswer(
    val intent: String,
    val answer: String,
    val citations: List<String> = emptyList(),
    val confidence: Double = 0.0,
    val shouldHandoff: Boolean = false
) {
    companion object {
        // JSON Schema (Structured Outputs)
        // - 실무에서는 intent enum을 더 엄격히 제한하는 걸 추천
        // 아래 필드가 없는 경우 “가끔 JSON 깨짐 / 필드 누락”이 무조건 발생함.
        // resources/schema/llm_answer.json 같은 파일로 빼고 버전 관리하는 걸 추천.
        val JSON_SCHEMA: String = """
        {
          "type": "object",
          "additionalProperties": false,
          "required": ["intent", "answer", "citations", "confidence", "shouldHandoff"],
          "properties": {
            "intent": { "type": "string" },
            "answer": { "type": "string" },
            "citations": { "type": "array", "items": { "type": "string" } },
            "confidence": { "type": "number", "minimum": 0, "maximum": 1 },
            "shouldHandoff": { "type": "boolean" }
          }
        }
        """.trimIndent()
    }
}