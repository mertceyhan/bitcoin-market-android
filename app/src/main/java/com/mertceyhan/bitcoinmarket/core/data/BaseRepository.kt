package com.mertceyhan.bitcoinmarket.core.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

abstract class BaseRepository {

    fun <T : Any> apiCall(call: suspend () -> T): Flow<State<T>> =
        flow {
            emit(State.Loading)
            emit(State.Success(data = call.invoke()))
        }.catch { error ->
            error.printStackTrace()
            emit(State.Error(error))
        }.flowOn(Dispatchers.IO)
}
