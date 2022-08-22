package com.example.ieshop.framework.repository

import com.example.core.data.repository.repository
import com.example.core.domain.product
import com.example.core.domain.user
import com.example.ieshop.framework.sourse.localSourse.LocalDatabase
import com.example.ieshop.utils.asUserDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class shopRepositoryLocal @Inject constructor(
    val localDB: LocalDatabase,
    val userManager: userManager
) : repository {

    override fun registrate(user: user): Boolean {
        return if (localDB.userDao().countOfUser(user.login) != 0) {
            localDB.userDao()
                .registrate(user.asUserDomain())
            true
        } else false
    }

    override suspend fun getUser(login: String): Flow<Boolean> {
        TODO("Not yet implemented")
    }

    /*
        override suspend fun getUser(login: String): Flow<Boolean> {
            TODO("Not yet implemented")
        }
    */
    /*
        override suspend fun getUser(login: String): Boolean {
            return localDB.userDao().getUserInfo(login).isNotEmpty()
        }
    */
    override suspend fun login(login: String, password: String): Boolean {
        return withContext(Dispatchers.IO) {
            localDB.userDao().login(login, password) > 0
        }
    }

    override fun getUsersByPage(page: Int): List<user> {
        TODO("Not yet implemented")
    }

    override fun getPurchaseByPage(page: Int): List<product> {
        TODO("Not yet implemented")
    }
}
