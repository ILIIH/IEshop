package com.example.ieshop.framework.repository

import com.example.core.data.repository.repository
import com.example.core.domain.error.UIState
import com.example.core.domain.product
import com.example.core.domain.user
import com.example.ieshop.framework.sourse.localSourse.LocalDatabase
import com.example.ieshop.framework.sourse.remoteSourse.ShopService
import com.example.ieshop.utils.asUserData
import com.example.ieshop.utils.asUserDomain
import com.example.ieshop.utils.networkBoundResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class shopRepository @Inject constructor(
    val localDB: LocalDatabase,
    val remoteDB: ShopService,
    val userManager: userManager
) : repository {

    override suspend fun registrate(user: user) = networkBoundResource(
        query = {
            flow {
                if (localDB.userDao().getUserInfo(user.login).isNotEmpty()) {
                    localDB.userDao().registrate(user.asUserDomain())
                    emit(user)
                } else throw Exception("RepeatCredentials")
            }
        },
        fetch = {
            remoteDB.registrate(
                user.name,
                user.surname,
                user.login,
                user.photo,
                user.telephone,
                user.password,
                user.country
            )
        }, shouldFetch = false
    )
        .stateIn(scope = CoroutineScope(Dispatchers.IO))

    override suspend fun getUser(login: String): Flow<UIState<user>> {
        TODO()
    }

    override suspend fun login(login: String, password: String) = networkBoundResource(
        query = {
            localDB.userDao().getUserInfo(login).asFlow().map { it.asUserData() }
        },
        fetch = {
            remoteDB.getUserInfo(login)
        },
        saveFetchResult = { user ->
            user.body()?.first()?.let { userManager.login(it) }
        }

    ).stateIn(scope = CoroutineScope(Dispatchers.IO))

    override fun getUsersByPage(page: Int): List<user> {
        TODO("Not yet implemented")
    }

    override fun getPurchaseByPage(page: Int): List<product> {
        TODO("Not yet implemented")
    }
}
