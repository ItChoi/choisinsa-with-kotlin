package com.mall.choisinsa.llmgateway.domain.rag.ingest

class TextChunker {
    fun chunk(
        text: String,
        maxChars: Int = 1200,
        overlap: Int = 150,
    ): List<String> {
        if (text.length <= maxChars) return listOf(text)

        val chunks = mutableListOf<String>()
        var start = 0

        while (start < text.length) {
            val end = minOf(text.length, start + maxChars)
            chunks += text.substring(start, end)
            if (end == text.length) break
            start = maxOf(0, end - overlap)
        }

        return chunks
    }
}