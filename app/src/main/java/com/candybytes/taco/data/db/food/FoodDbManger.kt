package com.candybytes.taco.data.db.food

import androidx.paging.PagingSource
import com.candybytes.taco.domain.model.Food
import javax.inject.Inject

class FoodDbManger @Inject constructor(
    private val db: FoodDb,
) {
    private val dao = db.foodDao()
    suspend fun getAllFood(): List<Food> {
        return dao.getAllAsync().map { it.toModel() }
    }
}
