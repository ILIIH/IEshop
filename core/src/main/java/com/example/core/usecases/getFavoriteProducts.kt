package com.example.core.usecases

import com.example.core.data.repository.repository

class getFavoriteProducts constructor(private val repository: repository) {
    suspend fun execute() = repository.getFavoriteProduct()
}