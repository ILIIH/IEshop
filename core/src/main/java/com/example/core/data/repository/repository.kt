package com.example.core.data.repository

import com.example.core.domain.error.UIState
import com.example.core.domain.product
import com.example.core.domain.user
import kotlinx.coroutines.flow.Flow

interface repository {
    suspend fun registrate(user: user): Flow<UIState<user>>
    suspend fun login(login: String, password: String): Flow<UIState<user>>
    suspend fun authorize(email: String, code: String, user: user)
    fun getCurrentUser(): user?
    fun getUsersByPage(page: Int): List<user>
    fun getProductsByPage(page: Int): List<product>
    suspend fun getAllProduct(): Flow<UIState<List<product>>>
    suspend fun getFavoriteProduct(): Flow<UIState<List<product>>>
    suspend fun getPurchese(): Flow<UIState<List<product>>>
}
