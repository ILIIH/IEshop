package com.example.core.usecases

import com.example.core.data.repository.repository

class getUserInfo constructor(private val repo: repository) {
     fun execute() = repo.getCurrentUser()
}