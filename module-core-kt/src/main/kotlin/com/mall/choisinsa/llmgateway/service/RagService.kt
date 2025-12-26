package com.mall.choisinsa.llmgateway.service

import org.springframework.ai.vectorstore.SearchRequest
import org.springframework.ai.vectorstore.VectorStore
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class RagService(
    private val vectorStore: VectorStore,
    @Value("\${llm.rag.top-k}") private val topK: Int,
    @Value("\${llm.rag.min-score}") private val minScore: Double
){
    data class RagContext(val contextText: String, val citations: List<String>)

    fun retrieve(query: String): RagContext {
        val request = SearchRequest.builder()
            .query(query)
            .topK(topK)
            .similarityThreshold(minScore) // 0~1
            .build()

        val results = vectorStore.similaritySearch(request)



        // 구현체에 따라 score 접근 방식이 다를 수 있어 “점수”는 우선 옵션으로 두고,
        // 실무에서는 결과가 너무 엉뚱할 때 threshold를 적용하는 식으로 발전시켜.
        val citations = results.mapNotNull { doc ->
            val source = doc.metadata["source"]?.toString() ?: return@mapNotNull null
            val chunk = doc.metadata["chunk"]?.toString() ?: "0"
            "$source#$chunk"
        }


        val context = results.joinToString("\n\n---\n\n") { doc ->
            val source = doc.metadata["source"]?.toString() ?: "unknown"
            val text = doc.text ?: ""
            "[source=$source]\n$text"
        }

        return RagContext(contextText = context, citations = citations)
    }

}