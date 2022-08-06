package com.example.core.useCases

import com.example.core.model.ErrorEntity
import com.example.core.model.Result
import com.example.core.model.user
import com.example.core.repository.repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class loginUser constructor(private val repository: repository) {

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
