package com.candybytes.taco.data.repository

import com.candybytes.taco.data.api.category.CategoryApiClient
import com.candybytes.taco.data.db.food.FoodDbManger
import com.candybytes.taco.domain.model.Category
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    private val categoryApiClient: CategoryApiClient,
    private val foodDbManger: FoodDbManger,
) {
    private val mutex = Mutex()
    private val categoriesFlow: MutableStateFlow<Result<List<Category>>> = MutableStateFlow(
        Result.success(
            emptyList()
        )
    )
    val categories: Flow<Result<List<Category>>> = categoriesFlow
        .onStart {
            loadCategories()
        }

    suspend fun loadCategories(forceNetwork: Boolean = false) {
        mutex.withLock {
            if (forceNetwork || categoriesFlow.value.getOrNull().orEmpty().isEmpty()) {
                try {
                    val food = foodDbManger.getAllFood()
                    val cts = categoryApiClient.getAllCategories()
                        .map { cat -> cat.copy(count = food.count { it.categoryId == cat.id }) }
                    categoriesFlow.value = Result.success(cts)
                } catch (t: Throwable) {
                    categoriesFlow.value = Result.failure(t)
                }
            }
        }
    }

}
