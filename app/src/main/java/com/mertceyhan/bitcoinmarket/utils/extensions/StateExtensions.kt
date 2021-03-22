@file:Suppress("unused", "EXPERIMENTAL_API_USAGE")

package com.mertceyhan.bitcoinmarket.utils.extensions

import com.mertceyhan.bitcoinmarket.base.data.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

fun <T, R> State<T>.map(transform: (T) -> R): State<R> {
    return when (this) {
        is State.Success -> State.Success(transform(data))
        is State.Error -> State.Error(exception)
        is State.Loading -> State.Loading
    }
}

fun <T> Flow<State<T>>.doOnSuccess(action: suspend (T) -> Unit): Flow<State<T>> =
    transform { value ->
        if (value is State.Success) {
            action(value.data)
        }
        return@transform emit(value)
    }

fun <T> Flow<State<T>>.doOnError(action: suspend (Throwable) -> Unit): Flow<State<T>> =
    transform { value ->
        if (value is State.Error) {
            action(value.exception)
        }
        return@transform emit(value)
    }

fun <T> Flow<State<T>>.doOnLoading(action: suspend () -> Unit): Flow<State<T>> =
    transform { value ->
        if (value is State.Loading) {
            action()
        }
        return@transform emit(value)
    }
