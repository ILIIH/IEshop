package com.example.ieshop.utils

import android.util.Log
import com.example.core.domain.error.ErrorEntity
import com.example.core.domain.error.UIState
import kotlinx.coroutines.flow.*

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit = {},
    crossinline shouldFetch:() -> Boolean = {true}
) = flow {
    Log.i("RepoLog", "Inside networkBoundResource")
    val data = query().last()

    val flow = if (shouldFetch()) {
        emit(UIState.Loading(data))
        try {
            val fetchRes = fetch()
            saveFetchResult(fetchRes)
            query().map { UIState.Success(it) }
        } catch (throwable: Throwable) {

            Log.i("RepoLog", "Catched Error ${throwable.message}")

            val error = when (throwable.message) {
                "RepeatCredentials" -> ErrorEntity.RepeatCredentials()
                "Sever error" -> ErrorEntity.ServerError()
                "UNIQUE constraint failed: UserDatabase.Login (code 1555 SQLITE_CONSTRAINT_PRIMARYKEY)" -> ErrorEntity.RepeatCredentials()
                "Incorrect password or login" -> ErrorEntity.WrongLoginOrPass()
                else -> ErrorEntity.DatabaceError()
            }
            query().map { UIState.Error(error, it) }
        }
    } else {
        query().map { UIState.Success(it) }
    }
    Log.i("RepoLog", "Going to emmit all ${flow.first()}")


    emitAll(flow)
}
