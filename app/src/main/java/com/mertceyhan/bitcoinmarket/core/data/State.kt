package com.mertceyhan.bitcoinmarket.core.data

sealed class State<out T> {

    class Success<T>(val data: T) : State<T>()

    class Error(val exception: Throwable) : State<Nothing>()

    object Loading : State<Nothing>()
}
