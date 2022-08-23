package com.example.core.usecases

import com.example.core.data.repository.repository
import com.example.core.domain.error.ErrorEntity
import com.example.core.domain.error.UIState
import com.example.core.domain.user
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

class login constructor(private val repository: repository) {

    suspend fun execute(user: user): UIState<user> {

        return withContext(Dispatchers.IO) {
            val encryptedPassword = StringBuffer()
            for (item in user.password) {
                encryptedPassword.append(item.code + 11)
            }
            val loginResult = repository.login(
                user.login,
                encryptedPassword.toString()
            ).first()

            return@withContext if (loginResult is  UIState.Success) UIState.Success(user)
            else loginResult
        }
    }
}
