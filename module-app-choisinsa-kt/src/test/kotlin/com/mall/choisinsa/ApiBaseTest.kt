package com.mall.choisinsa

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext
import org.springframework.restdocs.RestDocumentationContextProvider
import org.springframework.restdocs.RestDocumentationExtension
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration
import org.springframework.restdocs.operation.preprocess.Preprocessors.modifyHeaders
import org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint
import org.springframework.restdocs.templates.TemplateFormats
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext


//@AutoConfigureRestDocs(uriScheme = "http", uriHost = "localhost", uriPort = 8080)
@WebMvcTest
@ExtendWith(RestDocumentationExtension::class)
open class ApiBaseTest {
    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @MockitoBean
    lateinit var jpaMetamodelMappingContext: JpaMetamodelMappingContext

    @Autowired
    private lateinit var context: WebApplicationContext

    @BeforeEach // TODO: 문서 작성 후 @BeforeAll로 변경해도 되는지 체크
    fun setup(
        webApplicationContext: WebApplicationContext,
        restDocumentation: RestDocumentationContextProvider,
    ) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
            //.addFilters(CharacterEncodingFilter("UTF-8", true))
            .apply<DefaultMockMvcBuilder?>(
                documentationConfiguration(restDocumentation)
                    .operationPreprocessors()
                        //.withRequestDefaults(modifyHeaders().remove("Foo")) // 헤더 제거 가능
                        .withRequestDefaults(prettyPrint())
                        .withResponseDefaults(prettyPrint())
                    .and()
                        .uris()
                            .withScheme("http") // default = http
                            .withHost("localhost") // default = localhost
                            .withPort(8080) // default = 8080
                    .and()
                        .snippets()
                            .withTemplateFormat(TemplateFormats.asciidoctor()) // default = asciidoctor
        )
            .build()
    }
}