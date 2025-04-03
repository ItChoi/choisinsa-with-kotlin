package com.mall.choisinsa.brand.controller

import com.mall.choisinsa.ApiBaseTest
import com.mall.choisinsa.brand.domain.dto.search.BrandSearch
import com.mall.choisinsa.brand.fixture.dto.BrandWithItemCountFixture
import com.mall.choisinsa.brand.service.BrandService
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.any
import org.mockito.BDDMockito.given
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation
import org.springframework.restdocs.payload.PayloadDocumentation.*
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest(BrandController::class)
class BrandControllerTest(

) : ApiBaseTest() {

    @MockitoBean
    private lateinit var brandService: BrandService

    @Test
    @DisplayName("모든 브랜드를 조회한다. 브랜드에 포함된 상품 개수도 포함한다.")
    fun test() {
        // given
        val search = BrandSearch(
            listOf(1L, 2L, 3L),
            1L,
            "Nike",
            "나이키",
        )
        val responses = listOf(BrandWithItemCountFixture().response())


        // when
        given(brandService.findBrandsWithItemCountBy(search))
            .willReturn(responses)

        // then
        this.mockMvc.perform(get("/api/brands/with-item-count")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(search)))
            .andExpect(status().isOk)
            .andDo(document("get_brands_with_item_count",
                requestFields(
                    fieldWithPath("brandIds").type(JsonFieldType.ARRAY).description("브랜드 ids").optional(),
                    fieldWithPath("companyId").type(JsonFieldType.STRING).description("회사 id").optional(),
                    fieldWithPath("brandNameEn").type(JsonFieldType.STRING).description("브랜드 영문명").optional(),
                    fieldWithPath("brandNameKo").type(JsonFieldType.STRING).description("브랜드 국문명").optional(),
                ),
                responseFields(
                    fieldWithPath("status").description("http status"),
                    fieldWithPath("exceptionType").description("예외 타입"),
                    fieldWithPath("exceptionMsg").description("예외 메시지"),
                    fieldWithPath("data").description("응답 값"),
                    fieldWithPath("data.[].id").description("브랜드 id"),
                    fieldWithPath("data.[].companyId").description("회사 id"),
                    fieldWithPath("data.[].nameEn").description("브랜드 영문명"),
                    fieldWithPath("data.[].nameKo").description("브랜드 국문명"),
                    fieldWithPath("data.[].totalItemCount").description("총 아이템 개수"),

                )
            ))

    }

}

