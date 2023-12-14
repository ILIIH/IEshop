package com.example.core.usecases

import com.example.core.data.repository.repository

class getAllProducts constructor(private val repository: repository) {
    suspend fun execute() = repository.getAllProduct()
}