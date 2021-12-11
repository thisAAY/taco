package com.candybytes.taco.data.vo

import com.google.gson.annotations.SerializedName

data class Nutrient(
    /**
     */
    @SerializedName("unit")
    val unit: String = "",

    /**
     *
     */
    @SerializedName("qty")
    val qty: String = ""
) {
    fun toModel(): com.candybytes.taco.domain.model.Nutrient {
        return com.candybytes.taco.domain.model.Nutrient(unit, qty)
    }
}
