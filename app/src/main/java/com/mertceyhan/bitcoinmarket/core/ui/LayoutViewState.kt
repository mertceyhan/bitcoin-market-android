package com.mertceyhan.bitcoinmarket.core.ui

import android.content.Context
import com.mertceyhan.R
import com.mertceyhan.bitcoinmarket.core.ui.UiState
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

class LayoutViewState(
    private val uiState: UiState<*>
) {

    fun isLoading() = uiState is UiState.Loading

    fun isError() = uiState is UiState.Error

    fun isSuccess() = uiState is UiState.Success

    fun getErrorImage(): Int? = if (uiState is UiState.Error) {
        when (uiState.exception) {
            is IOException -> R.drawable.ic_no_connection
            else -> R.drawable.ic_error
        }
    } else {
        null
    }

    fun getErrorMessage(context: Context): String =
        if (uiState is UiState.Error) {
            when (uiState.exception) {
                is HttpException -> uiState.exception.message()
                is SocketTimeoutException -> context.getString(R.string.timeout_error_message)
                is IOException -> context.getString(R.string.no_internet_connection)
                else -> context.getString(R.string.something_went_wrong)
            }
        } else {
            ""
        }
}
