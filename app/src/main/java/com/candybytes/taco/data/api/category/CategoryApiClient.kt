package com.candybytes.taco.data.api.category

import com.candybytes.taco.data.api.TacoService
import com.candybytes.taco.domain.model.Category
import javax.inject.Inject

class CategoryApiClient @Inject constructor(
    private val client: TacoService,
) {
    suspend  fun getCategoryFromId(id: Int): Category{
        return client.getCategoryAsync(id).toModel()
    }
    suspend fun getAllCategories(): List<Category>{
        return client.getAllCategoriesAsync().map { it.toModel() }
    }
}
