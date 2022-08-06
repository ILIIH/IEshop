package com.example.core.repository

import com.example.core.model.product
import com.example.core.model.user

interface repository {
    fun registrate(
        username: String,
        name: String,
        surname: String,
        login: String,
        photo: String?,
        telephone: String,
        password: String
    ): Boolean
    suspend fun getUser(login: String): Boolean
    suspend fun login(login: String, password: String): Boolean
    fun getUsersByPage(page: Int): List<user>
    fun getPurchaseByPage(page: Int): List<product>
}
