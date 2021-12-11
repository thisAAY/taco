package com.candybytes.taco.data.vo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "categories")
data class Category(

    /**
     * Category unique ID.
     */
    @SerializedName("id")
    @PrimaryKey(autoGenerate = false)
    val id: Int = -1,

    /**
     * Category description.
     */
    @SerializedName("category")
    val name: String = ""

) {
    fun toModel(): com.candybytes.taco.domain.model.Category {
        return com.candybytes.taco.domain.model.Category(
            id, name
        )
    }
}
