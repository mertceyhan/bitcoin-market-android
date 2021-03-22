package com.mertceyhan.bitcoinmarket.base.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

abstract class BaseRepository {

    suspend fun <T : Any> apiCall(call: suspend () -> T): Flow<State<T>> =
        withContext(Dispatchers.IO) {
            flow {
                try {
                    this.emit(State.Loading)

                    val response = call.invoke()

                    this.emit(State.Success(data = response))
                } catch (e: Exception) {
                    e.printStackTrace()
                    this.emit(State.Error(e))
                }
            }
        }
}
