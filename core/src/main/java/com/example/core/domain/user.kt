package com.example.core.domain
import java.io.Serializable

data class user(
    val name: String,
    val surname: String,
    val login: String,
    val email: String,
    val photo: String,
    val telephone: String,
    val lotsList: List<product>,
    val purchaseList: List<product>,
    val password: String,
    val country: String
) : Serializable
