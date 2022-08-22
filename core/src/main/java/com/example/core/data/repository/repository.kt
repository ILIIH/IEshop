package com.example.core.data.repository

import com.example.core.domain.product
import com.example.core.domain.user
import kotlinx.coroutines.flow.Flow

interface repository {
    fun registrate(user: user): Boolean
    suspend fun getUser(login: String): Flow<Boolean>
    suspend fun login(login: String, password: String): Boolean
    fun getUsersByPage(page: Int): List<user>
    fun getPurchaseByPage(page: Int): List<product>
}
