package com.example.core.domain

data class product(
    val name: String,
    val cost: Int,
    val type: String,
    val publishData: String,
    val photos: List<String>,
    val ownerLogin: String,
    val id: Int,
    val orderLogin: String? = null,
    val orderDate: String?= null
)
