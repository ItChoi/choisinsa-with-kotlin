package com.mall.choisinsa.llmgateway.config

import com.mall.choisinsa.llmgateway.domain.rag.ingest.KnowledgeIngestor
import org.springframework.ai.embedding.EmbeddingModel
import org.springframework.ai.vectorstore.SimpleVectorStore
import org.springframework.ai.vectorstore.VectorStore
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

// VectorStore + Ingestor 실행
@Configuration
class RagConfig(
    @Value("\${llm.rag.ingest-on-startup:true}") private val ingestOnStartup: Boolean

) {

    /**
     * 애플리케이션 부팅 -> resources/knowledge/ .md를 읽어 벡티스토어에 적재
     * - 실무에서는 “문서 파이프라인(ETL)”을 따로 돌리는 경우가 많지만, 학습/프로토타입은 이게 최고로 단순함
     */
    @Bean
    fun ingestRunner(ingestor: KnowledgeIngestor): ApplicationRunner =
        ApplicationRunner {
            if (!ingestOnStartup) return@ApplicationRunner

            runCatching {
                ingestor.ingestClasspathMarkdown("knowledge")
            }.onFailure { e ->
                // ⚠️ 부팅은 계속, RAG는 비활성 상태로 시작
                // 로그만 남기고 죽지 않게 처리
                println("⚠️ RAG ingest failed. App will start without RAG. cause=${e.message}")
            }
        }

    @Bean
    fun vectorStore(embeddingModel: EmbeddingModel): VectorStore {
        // SimpleVectorStore는 Spring AI가 제공하는 교육용 구현체 :contentReference[oaicite:5]{index=5}
        return SimpleVectorStore.builder(embeddingModel).build()
    }

}