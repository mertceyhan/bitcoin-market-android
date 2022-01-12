package com.mertceyhan.bitcoinmarket.features.market.ui.widget

import android.os.Build
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mertceyhan.R
import com.mertceyhan.bitcoinmarket.utils.`should be`
import com.mertceyhan.bitcoinmarket.widget.ui.BitcoinWidgetUiState
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@Config(sdk = [Build.VERSION_CODES.P])
@RunWith(AndroidJUnit4::class)
class BitcoinWidgetStateTest {

    private val currentPrice = "25.0000"
    private val changeRate = "%2.3"
    private lateinit var bitcoinWidgetUiState: BitcoinWidgetUiState

    @Test
    fun `when changeRate is POSITIVE, then getRateBackground returns background_widget_positive_rate `() {

        bitcoinWidgetUiState = BitcoinWidgetUiState(currentPrice, changeRate, true)

        val result = bitcoinWidgetUiState.getRateBackground()

        result `should be` R.drawable.background_widget_positive_rate

    }

    @Test
    fun `when changeRate is POSITIVE, then getRateArrow returns ic_arrow_positive_white `() {

        bitcoinWidgetUiState = BitcoinWidgetUiState(currentPrice, changeRate, true)

        val result = bitcoinWidgetUiState.getRateArrow()

        result `should be` R.drawable.ic_arrow_positive_white

    }

    @Test
    fun `when changeRate is NEGATIVE, then getRateBackground returns background_widget_negative_rate `() {

        bitcoinWidgetUiState = BitcoinWidgetUiState(currentPrice, changeRate, false)

        val result = bitcoinWidgetUiState.getRateBackground()

        result `should be` R.drawable.background_widget_negative_rate

    }

}