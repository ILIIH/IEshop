package com.example.ieshop.utils

import com.example.core.domain.product
import com.example.core.domain.user
import com.example.ieshop.framework.sourse.entities.ProductDatabase
import com.example.ieshop.framework.sourse.entities.ProductNetwork
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

fun ProductDatabase.asProductData(): product = product(
    name,
    cost,
    type,
    publishData,
    photos.split("||"),
    ownerLogin
)
fun ProductNetwork.asProductData(): product = product(
    name,
    cost,
    type,
    publishData,
    photos.split("||"),
    ownerLogin
)

fun ProductNetwork.asProductDataBace(): ProductDatabase = ProductDatabase(
    name,
    cost,
    type,
    publishData,
    photos,
    ownerLogin
)

fun UserDatabase.asUserData(): user = user(
    name, surname, login, email, photo, telephone, listOf(), listOf(), password, country
)
fun UserNetwork.asUserData(): user = user(
    name, surname, login, email, photo, telephone, listOf(), listOf(), password, country
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
