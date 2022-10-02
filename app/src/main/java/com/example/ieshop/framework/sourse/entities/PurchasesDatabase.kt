package com.example.ieshop.framework.sourse.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PurchasesDatabase(
    val name: String,
    val cost: Int,
    val type: String,
    val buyData: String,
    val photos: List<String>,
    val ownerLogin: String,
    @PrimaryKey(autoGenerate = true) val id: Int
)
