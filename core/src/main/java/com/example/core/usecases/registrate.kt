package com.example.core.usecases

import com.example.core.data.repository.repository
import com.example.core.domain.user

class registrate constructor(private val repository: repository) {

    fun execute(user: user): Boolean {
        val encryptedPassword = StringBuffer()
        for (item in user.password) {
            encryptedPassword.append(item.code + 11)
        }
        return repository.registrate(user(
            user.name,
            user.surname,
            user.login,
            user.email,
            user.photo,
            user.telephone,
            user.lotsList,
            user.purchaseList,
            encryptedPassword.toString(),
            user.country
        ))
    }
}
