@file:Suppress("unused", "EXPERIMENTAL_API_USAGE")

package com.mertceyhan.bitcoinmarket.utils.extensions

import com.mertceyhan.bitcoinmarket.core.ui.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

fun <T, R> UiState<T>.map(transform: (T) -> R): UiState<R> {
    return when (this) {
        is UiState.Success -> UiState.Success(transform(data))
        is UiState.Error -> UiState.Error(exception)
        is UiState.Loading -> UiState.Loading
    }
}

fun <T> Flow<UiState<T>>.doOnSuccess(action: suspend (T) -> Unit): Flow<UiState<T>> =
    transform { value ->
        if (value is UiState.Success) {
            action(value.data)
        }
        return@transform emit(value)
    }

fun <T> Flow<UiState<T>>.doOnError(action: suspend (Throwable) -> Unit): Flow<UiState<T>> =
    transform { value ->
        if (value is UiState.Error) {
            action(value.exception)
        }
        return@transform emit(value)
    }

fun <T> Flow<UiState<T>>.doOnLoading(action: suspend () -> Unit): Flow<UiState<T>> =
    transform { value ->
        if (value is UiState.Loading) {
            action()
        }
        return@transform emit(value)
    }
