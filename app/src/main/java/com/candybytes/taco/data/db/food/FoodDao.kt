package com.candybytes.taco.data.db.food

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.candybytes.taco.data.vo.Food

/**
 * Interface for database access for Food related operations.
 */
@Dao
interface FoodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAsync(food: Food)

    @Query("SELECT * FROM food")
    suspend fun getAllPageAsync(): PagingSource<Int,Food>

    @Query("SELECT * FROM food")
    suspend fun getAllAsync(): List<Food>

}
