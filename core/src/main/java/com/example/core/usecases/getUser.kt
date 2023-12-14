package com.example.core.usecases

import com.example.core.data.repository.repository

class getUser constructor(private val repository: repository) {
    fun getCurrentUser() = repository.getCurrentUser()
}
