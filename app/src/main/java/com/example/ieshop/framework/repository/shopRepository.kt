package com.example.ieshop.framework.repository

import com.example.core.data.repository.repository
import com.example.core.domain.error.ErrorEntity
import com.example.core.domain.error.UIState
import com.example.core.domain.product
import com.example.core.domain.user
import com.example.ieshop.framework.sourse.entities.User
import com.example.ieshop.framework.sourse.localSourse.LocalDatabase
import com.example.ieshop.framework.sourse.remoteSourse.ShopService
import com.example.ieshop.utils.asUserData
import com.example.ieshop.utils.asUserDomain
import com.example.ieshop.utils.networkBoundResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

open class shopRepository @Inject constructor(
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
                } else UIState.Error(ErrorEntity.RepeatCredentials,null)
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
        },
        saveFetchResult = { user ->
            user.body()?.asUserDomain()?.let { userManager.login(it) }
        }
    )

    override suspend fun getUser(login: String): Flow<UIState<user>> {
        return flow { emit(UIState.Success(user("-","-","-","-","-","-", listOf(), listOf(),"-","-" ))) }
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

    )

    override fun getUsersByPage(page: Int): List<user> {
        return listOf()
    }

    override fun getPurchaseByPage(page: Int): List<product> {
        return listOf()
    }
}
