package com.candybytes.taco.data.db.food

import androidx.paging.PagingSource
import javax.inject.Inject

class FoodDbManger @Inject constructor(
    private val foodDb: FoodDao
) {
    suspend fun getFood(): PagingSource<Int, com.candybytes.taco.data.vo.Food> {
        return foodDb.getAllAsync()
    }
}
