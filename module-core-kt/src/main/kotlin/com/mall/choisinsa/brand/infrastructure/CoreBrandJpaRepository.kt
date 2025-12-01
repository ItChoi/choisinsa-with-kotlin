package com.mall.choisinsa.brand.infrastructure

import com.mall.choisinsa.brand.domain.BrandEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CoreBrandJpaRepository : JpaRepository<BrandEntity, Long>{
}