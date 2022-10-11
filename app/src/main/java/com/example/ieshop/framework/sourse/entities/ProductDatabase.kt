package com.example.ieshop.framework.sourse.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class ProductDatabase(
    val name: String,
    val cost: Int,
    val type: String,
    val publishData: String,
    val photos: String,
    val ownerLogin: String,
    @PrimaryKey val id: Int,
    val orderLogin: String,
    val orderDate: String
)
