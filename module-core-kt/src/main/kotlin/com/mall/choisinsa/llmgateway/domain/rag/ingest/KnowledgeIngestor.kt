package com.mall.choisinsa.llmgateway.domain.rag.ingest

import org.springframework.ai.document.Document
import org.springframework.ai.embedding.EmbeddingModel
import org.springframework.ai.vectorstore.VectorStore
import org.springframework.core.io.support.PathMatchingResourcePatternResolver
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets


@Component
class KnowledgeIngestor(
    private val vectorStore: VectorStore,
    private val embeddingModel: EmbeddingModel,
    private val chunker: TextChunker = TextChunker(),
) {
    fun ingestClasspathMarkdown(dir: String) {
        val resolver = PathMatchingResourcePatternResolver()
        val resources = resolver.getResources("classpath*:$dir/*.md")

        val docs = resources.flatMap { res ->
            val text = res.inputStream.readAllBytes().toString(StandardCharsets.UTF_8)
            val chunks = chunker.chunk(text)
            chunks.mapIndexed { idx, chunk ->
                Document(
                    chunk,
                    mapOf(
                        "source" to (res.filename ?: "unknown"),
                        "chunk" to idx.toString()
                    )
                )
            }
        }

        // VectorStore는 내부에서 EmbeddingModel을 사용해 벡터화/저장
        vectorStore.add(docs)

    }
}