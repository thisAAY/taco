package com.candybytes.taco.domain.model

data class Food(
    val id: Int,
    val description: String,
    val baseQty: Int,
    val baseUnity: String,
    val categoryId: Int,
    val nutrients: Map<String, Nutrient>,
)
