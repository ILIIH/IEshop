package com.example.ieshop.framework.sourse.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    val name: String,
    val cost: Int,
    val type: String,
    val publishData: String,
    val photos: List<String>,
    val ownerLogin: String,
    @PrimaryKey(autoGenerate = true) val id: Int

)
