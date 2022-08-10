package com.example.core.data.repository

import com.example.core.domain.product
import com.example.core.domain.user

interface repository {
    fun registrate(user: user): Boolean
    suspend fun getUser(login: String): Boolean
    suspend fun login(login: String, password: String): Boolean
    fun getUsersByPage(page: Int): List<user>
    fun getPurchaseByPage(page: Int): List<product>
}
