package com.example.core.model

data class product(
    val name: String,
    val cost: Int,
    val type: String,
    val publishData: String,
    val photos: List<String>,
    val ownerLogin: String
)
