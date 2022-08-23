package com.example.ieshop.framework.repository

import com.example.core.data.repository.repository
import com.example.core.domain.product
import com.example.core.domain.user
import com.example.ieshop.framework.sourse.remoteSourse.ShopService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
/*
class shopRepositoryRemote @Inject constructor(
    val remoteDB: ShopService,
    val userManager: userManager
) : repository {

    override fun registrate(user: user): Boolean {
        return if (remoteDB.countOfUser(user.login).body()?.isNotEmpty() == true) {
            remoteDB.registrate(
                user.name,
                user.surname,
                user.login,
                user.photo,
                user.telephone,
                user.password,
                user.country
            ).isSuccessful
            true
        } else false
    }

 /*   override suspend fun getUser(login: String): Boolean {
        return remoteDB.getUserInfo(login).body()!!.isNotEmpty()
    }
*/
    override suspend fun getUser(login: String): Flow<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun login(login: String, password: String): Boolean {
        return withContext(Dispatchers.IO) {
            remoteDB.login(login, password).isSuccessful
        }
    }

    override fun getUsersByPage(page: Int): List<user> {
        TODO("Not yet implemented")
    }

    override fun getPurchaseByPage(page: Int): List<product> {
        TODO("Not yet implemented")
    }
}
*/