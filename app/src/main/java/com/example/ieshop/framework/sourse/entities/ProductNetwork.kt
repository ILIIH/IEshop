package com.example.ieshop.framework.sourse.entities

import com.google.gson.annotations.SerializedName

data class ProductNetwork(
    @field:SerializedName("name") val name: String,
    @field:SerializedName("cost") val cost: Int,
    @field:SerializedName("type") val type: String,
    @field:SerializedName("publishData") val publishData: String,
    @field:SerializedName("photos") val photos: String,
    @field:SerializedName("ownerLogin") val ownerLogin: String,
    @field:SerializedName("id") val id: Int
)
