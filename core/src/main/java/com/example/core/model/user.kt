package com.example.core.model

data class user(
    val name: String,
    val surname: String,
    val login: String,
    val photo: String?,
    val telephone: String,
    val lotsList: List<product>?,
    val purchaseList: List<product>?,
    val password: String
)
