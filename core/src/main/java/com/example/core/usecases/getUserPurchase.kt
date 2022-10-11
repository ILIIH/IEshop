package com.example.core.usecases

import com.example.core.data.repository.repository
import com.example.core.domain.user

class getUserPurchase constructor(private val repo: repository) {
    suspend fun execute() = repo.getPurchese()
}