package com.candybytes.taco.domain.model

data class Category(
    val id: Int,
    val name: String,
    val count: Int = 0,
)
