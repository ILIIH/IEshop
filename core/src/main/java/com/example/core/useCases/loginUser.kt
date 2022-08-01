package com.example.core.useCases

import com.example.core.model.user
import com.example.core.repository.repository

class loginUser constructor(private val repository: repository) {

    fun login(user: user): Boolean {
        val encryptedPassword = StringBuffer()
        for (item in user.password) {
            encryptedPassword.append(item.code + 11)
        }
        return repository.login(user.login, encryptedPassword.toString()) != null
    }
}
