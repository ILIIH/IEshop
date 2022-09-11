package com.example.ieshop.utils

import com.example.core.domain.user
import com.example.ieshop.framework.sourse.entities.User

fun user.asUserDomain(): User = User(
    name,
    surname,
    email,
    login,
    photo,
    telephone,
    password,
    country
)

fun User.asUserData(): user = user(
    name, surname,login,email,photo,telephone, listOf(), listOf(),password,country
)