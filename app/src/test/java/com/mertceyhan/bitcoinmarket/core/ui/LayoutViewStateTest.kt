package com.mertceyhan.bitcoinmarket.core.ui

import android.content.Context
import android.os.Build
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mertceyhan.R
import com.mertceyhan.bitcoinmarket.base.data.State
import com.mertceyhan.bitcoinmarket.utils.`should be`
import io.mockk.every
import io.mockk.mockk
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

@Config(sdk = [Build.VERSION_CODES.P])
@RunWith(AndroidJUnit4::class)
class LayoutViewStateTest {

    private val context = ApplicationProvider.getApplicationContext<Context>()

    private val httpException = mockk<HttpException>()

    private lateinit var layoutViewState: com.mertceyhan.bitcoinmarket.base.ui.LayoutViewState

    @Test
    fun `when state is Loading, then isLoading() returns true`() {
        // given
        layoutViewState = com.mertceyhan.bitcoinmarket.base.ui.LayoutViewState(State.Loading)

        // when
        val result = layoutViewState.isLoading()

        // then
        result `should be` true
    }

    @Test
    fun `when state is not Loading, then isLoading() returns false`() {
        // given
        layoutViewState = com.mertceyhan.bitcoinmarket.base.ui.LayoutViewState(State.Success(null))

        // when
        val result = layoutViewState.isLoading()

        // then
        result `should be` false
    }

    @Test
    fun `when state is Error, then isError() returns true`() {
        // given
        layoutViewState =
            com.mertceyhan.bitcoinmarket.base.ui.LayoutViewState(State.Error(Exception()))

        // when
        val result = layoutViewState.isError()

        // then
        result `should be` true
    }

    @Test
    fun `when state is not Error, then isError() returns false`() {
        // given
        layoutViewState = com.mertceyhan.bitcoinmarket.base.ui.LayoutViewState(State.Success(null))

        // when
        val result = layoutViewState.isError()

        // then
        result `should be` false
    }

    @Test
    fun `when state is Success, then isSuccess() returns true`() {
        // given
        layoutViewState = com.mertceyhan.bitcoinmarket.base.ui.LayoutViewState(State.Success(true))

        // when
        val result = layoutViewState.isSuccess()

        // then
        result `should be` true
    }

    @Test
    fun `when state is not Success, then isSuccess() returns false`() {
        // given
        layoutViewState = com.mertceyhan.bitcoinmarket.base.ui.LayoutViewState(State.Loading)

        // when
        val result = layoutViewState.isSuccess()

        // then
        result `should be` false
    }

    @Test
    fun `when state is Error and exception is IO, then getErrorImage() returns ic_no_connection`() {
        // given
        layoutViewState =
            com.mertceyhan.bitcoinmarket.base.ui.LayoutViewState(State.Error(IOException()))

        // when
        val result = layoutViewState.getErrorImage()

        // then
        result `should be` R.drawable.ic_no_connection
    }

    @Test
    fun `when state is Error and exception is not IO, then getErrorImage() returns ic_error`() {
        // given
        layoutViewState =
            com.mertceyhan.bitcoinmarket.base.ui.LayoutViewState(State.Error(NullPointerException()))

        // when
        val result = layoutViewState.getErrorImage()

        // then
        result `should be` R.drawable.ic_error
    }

    @Test
    fun `when state is not Error, then getErrorImage() returns null`() {
        // given
        layoutViewState = com.mertceyhan.bitcoinmarket.base.ui.LayoutViewState(State.Loading)

        // when
        val result = layoutViewState.getErrorImage()

        // then
        result `should be` null
    }

    @Test
    fun `when exception is Http, then getErrorMessage() returns http message`() {
        // given
        every { httpException.message() } returns "Error Message"
        layoutViewState =
            com.mertceyhan.bitcoinmarket.base.ui.LayoutViewState(State.Error(httpException))

        // when
        val result = layoutViewState.getErrorMessage(context)

        // then
        result `should be` "Error Message"
    }

    @Test
    fun `when exception is IO, then getErrorMessage() returns no_internet_connection`() {
        // given
        layoutViewState =
            com.mertceyhan.bitcoinmarket.base.ui.LayoutViewState(State.Error(IOException()))

        // when
        val result = layoutViewState.getErrorMessage(context)

        // then
        result `should be` context.getString(R.string.no_internet_connection)
    }

    @Test
    fun `when exception is SocketTimeout, then getErrorMessage() returns timeout_error_message`() {
        // given
        layoutViewState =
            com.mertceyhan.bitcoinmarket.base.ui.LayoutViewState(State.Error(SocketTimeoutException()))

        // when
        val result = layoutViewState.getErrorMessage(context)

        // then
        result `should be` context.getString(R.string.timeout_error_message)
    }

    @Test
    fun `when exception is not one of these, then getErrorMessage() returns something_went_wrong`() {
        // given
        layoutViewState =
            com.mertceyhan.bitcoinmarket.base.ui.LayoutViewState(State.Error(NullPointerException()))

        // when
        val result = layoutViewState.getErrorMessage(context)

        // then
        result `should be` context.getString(R.string.something_went_wrong)
    }

    @Test
    fun `when state is not Error, then getErrorMessage() returns empty`() {
        // given
        layoutViewState = com.mertceyhan.bitcoinmarket.base.ui.LayoutViewState(State.Loading)

        // when
        val result = layoutViewState.getErrorMessage(context)

        // then
        result `should be` ""
    }
}
