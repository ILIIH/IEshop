package com.example.ieshop.framework.sourse.entities

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class PurchasesNetwork (
    @field:SerializedName("name") val name: String,
    @field:SerializedName("cost") val cost: Int,
    @field:SerializedName("type") val type: String,
    @field:SerializedName("buyData") val buyData: String,
    @field:SerializedName("Photos") val photos: List<String>,
    @field:SerializedName("ownerLogin") val ownerLogin: String,
    @field:SerializedName("id") val id: Int
        )