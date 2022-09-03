package com.example.ieshop.utils

import com.example.core.domain.error.ErrorEntity
import com.example.core.domain.error.UIState
import kotlinx.coroutines.flow.*

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit = {},
    crossinline shouldFetch:() -> Boolean = {true}
) = flow {
    val data = query().first()

    val flow = if (shouldFetch()) {
        emit(UIState.Loading(data))

        try {
            saveFetchResult(fetch())
            query().map { UIState.Success(it) }
        } catch (throwable: Throwable) {
            val error = when (throwable.message) {
                "RepeatCredentials" -> ErrorEntity.RepeatCredentials
                else -> ErrorEntity.DatabaceError
            }
            query().map { UIState.Error(error, it) }
        }
    } else {
        query().map { UIState.Success(it) }
    }

    emitAll(flow)
}
