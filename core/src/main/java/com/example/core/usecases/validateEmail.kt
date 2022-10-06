package com.example.core.usecases

import com.example.core.data.repository.repository
import com.example.core.domain.user

class validateEmail constructor(private val repository: repository) {
    suspend fun execute(user: user): Int {
        val code = (1000..9999).random()
        repository.authorize(user.email, code.toString(), user)
        return code
    }
}
