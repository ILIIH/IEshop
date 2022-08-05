package com.example.core.useCases

import com.example.core.model.ErrorEntity
import com.example.core.model.Result
import com.example.core.model.user
import com.example.core.repository.repository

class loginUser constructor(private val repository: repository) {

    fun execute(user: user): Result<user> {
        val encryptedPassword = StringBuffer()
        for (item in user.password) {
            encryptedPassword.append(item.code + 11)
        }

        return if (repository.login(user.login, encryptedPassword.toString()) != null) Result.Success(user)
        else Result.Error(ErrorEntity.DatabaceError)
    }
}
