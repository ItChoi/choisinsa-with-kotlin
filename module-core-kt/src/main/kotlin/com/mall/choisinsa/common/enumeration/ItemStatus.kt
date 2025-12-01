package com.mall.choisinsa.common.enumeration

enum class ItemStatus(
    val desc: String,
) {
    READY("준비중"),
    INPUT("입력중"),
    ON_SALE("판매중"),
    SOLD_OUT("품절"),
    TEMP_SOLD_OUT("일시 품절"),
    END_SALE("판매 종료"),
}