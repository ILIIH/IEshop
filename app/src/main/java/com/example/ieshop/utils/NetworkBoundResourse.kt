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
    val data = query().first()

    val flow = if (shouldFetch()) {
        emit(UIState.Loading(data))
        try {
            val fetchRes = fetch()
            Log.i("RepoLog", "Fetch Result  ${fetchRes}")
            saveFetchResult(fetchRes)
            query().map { UIState.Success(it) }
        } catch (throwable: Throwable) {

            Log.i("RepoLog", "Catched Error ${throwable.message}")

            val error = when (throwable.message) {
                "RepeatCredentials" -> ErrorEntity.RepeatCredentials()
                "Sever error" -> ErrorEntity.ServerError()
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
