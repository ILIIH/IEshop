package com.example.ieshop.utils

import android.util.Log
import com.example.core.domain.error.ErrorEntity
import com.example.core.domain.error.UIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
) = flow {
    val data = query().first()

    val flow = if (shouldFetch(data)) {
        emit(UIState.Loading(data))
        try {
            saveFetchResult(fetch())
            query().map { UIState.Success(it) }
        } catch (throwable: Throwable) {
            query().map { UIState.Error(ErrorEntity.DatabaceError, it) }
        }
    } else {
        query().map { UIState.Success(it) }
    }

    emitAll(flow)
}.flowOn(Dispatchers.IO)
