package com.candybytes.taco.data.api.food

import com.candybytes.taco.data.api.TacoService
import com.candybytes.taco.domain.model.Food
import javax.inject.Inject

class FoodApiClient @Inject constructor(
    private val client: TacoService,
) {
    suspend fun getAllFood(): List<Food> {
        return client.getAllFoodAsync().map { it.toModel() }
    }
}
