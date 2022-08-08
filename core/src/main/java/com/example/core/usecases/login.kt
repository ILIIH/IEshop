package com.example.core.usecases

import com.example.core.domain.error.ErrorEntity
import com.example.core.domain.Result
import com.example.core.domain.user
import com.example.core.data.repository.repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class login constructor(private val repository: repository) {

    suspend fun execute(user: user): Result<user> {
        return withContext(Dispatchers.IO) {
            val encryptedPassword = StringBuffer()
            for (item in user.password) {
                encryptedPassword.append(item.code + 11)
            }

            return@withContext if (repository.login(
                    user.login,
                    encryptedPassword.toString()
                )
            ) Result.Success(user)
            else Result.Error(ErrorEntity.DatabaceError)
        }

    }
}
