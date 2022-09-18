package com.example.core.usecases

import android.util.Log
import com.example.core.data.repository.repository
import com.example.core.domain.error.ErrorEntity
import com.example.core.domain.error.UIState
import com.example.core.domain.user
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

open class login constructor(private val repository: repository) {

    open suspend fun execute(user: user): Flow<UIState<user>> {

        return withContext(Dispatchers.IO) {
            val encryptedPassword = StringBuffer()
            for (item in user.password) {
                encryptedPassword.append(item.code + 11)
            }
            return@withContext repository.login(
                user.login,
                encryptedPassword.toString()
            ).catch { e->
                Log.i("RepoLog", "Error chached + "+e.message)
                emit(UIState.Error(ErrorEntity.DatabaceError()))
            }
        }
    }
}
