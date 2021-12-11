package com.candybytes.taco.data.db.food

import com.candybytes.taco.domain.model.Food
import javax.inject.Inject

class FoodDbManger @Inject constructor(
    private val foodDb: FoodDao
) {
    suspend fun getFood(): List<Food>{
        return foodDb.getAllAsync().map { it.toModel() }
    }
}
