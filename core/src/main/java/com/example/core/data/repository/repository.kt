package com.example.core.data.repository

import com.example.core.domain.error.UIState
import com.example.core.domain.product
import com.example.core.domain.user
import kotlinx.coroutines.flow.Flow

interface repository {
    suspend fun registrate(user: user): Flow<UIState<user>>
    suspend fun getUser(login: String): Flow<UIState<user>>
    suspend fun login(login: String, password: String): Flow<UIState<user>>
    fun getUsersByPage(page: Int): List<user>
    fun getPurchaseByPage(page: Int): List<product>
}
