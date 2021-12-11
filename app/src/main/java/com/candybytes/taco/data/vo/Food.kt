package com.candybytes.taco.data.vo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "food")
data class Food(
    /**
     * Food unique ID.
     */
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    val id: Int = -1,

    /**
     * Food description
     */
    @SerializedName("description")
    val description: String = "",

    /**
     * Amount
     */
    @SerializedName("base_qty")
    val baseQty: Int = -1,

    /**
     * Unit of amount
     */
    @SerializedName("base_unit")
    val baseUnity: String = "",

    /**
     * Category which the food belong
     */
    @SerializedName("category_id")
    val categoryId: Int = -1,

    /**
     * List of nutrients
     */
    @SerializedName("attributes")
    val nutrients: Map<String, Nutrient> = hashMapOf()
) {
    fun toModel(): com.candybytes.taco.domain.model.Food {
        return com.candybytes.taco.domain.model.Food(
            id,
            description,
            baseQty,
            baseUnity,
            categoryId,
            nutrients.mapValues { it.value.toModel() }
        )
    }
}
