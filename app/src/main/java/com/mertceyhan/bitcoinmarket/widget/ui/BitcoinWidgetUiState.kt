package com.mertceyhan.bitcoinmarket.widget.ui

import androidx.annotation.VisibleForTesting
import com.mertceyhan.R

class BitcoinWidgetUiState(
    val currentPrice: String,
    val changeRate: String,
    val isChangeRatePositive: Boolean
) {
    @VisibleForTesting
    fun getRateBackground() = if (isChangeRatePositive) {
        R.drawable.background_widget_positive_rate
    } else {
        R.drawable.background_widget_negative_rate
    }

    @VisibleForTesting
    fun getRateArrow() = if (isChangeRatePositive) {
        R.drawable.ic_arrow_positive_white
    } else {
        R.drawable.ic_arrow_negative_white
    }

}