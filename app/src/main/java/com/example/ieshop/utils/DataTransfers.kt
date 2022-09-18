package com.example.ieshop.utils

import com.example.core.domain.user
import com.example.ieshop.framework.sourse.entities.UserDatabase
import com.example.ieshop.framework.sourse.entities.UserNetwork

fun user.asUserDomain(): UserDatabase = UserDatabase(
    name,
    surname,
    email,
    login,
    photo,
    telephone,
    password,
    country
)

fun UserDatabase.asUserData(): user = user(
    name, surname,login,email,photo,telephone, listOf(), listOf(),password,country
)
fun UserNetwork.asUserData(): user = user(
    name, surname,login,email,photo,telephone, listOf(), listOf(),password,country
)
fun UserNetwork.asUserDatabace(): UserDatabase = UserDatabase(
    name,
    surname,
    email,
    login,
    photo,
    telephone,
    password,
    country
)