package com.mertceyhan.bitcoinmarket.features.market.ui

import android.content.Context
import androidx.annotation.VisibleForTesting
import com.github.mikephil.charting.data.LineDataSet
import com.mertceyhan.R
import com.mertceyhan.bitcoinmarket.features.market.domain.model.MarketInformation
import com.mertceyhan.bitcoinmarket.features.market.domain.model.MarketInformationChangeStatus
import com.mertceyhan.bitcoinmarket.features.market.domain.model.MarketInformationTimespan
import com.mertceyhan.bitcoinmarket.utils.extensions.getCompatColor
import com.mertceyhan.bitcoinmarket.utils.extensions.getCompatDrawable

class MarketViewState(
    val marketInformation: MarketInformation
) {

    fun getLineDataSet(context: Context) =
        LineDataSet(marketInformation.chartEntries, "market_price").apply {
            mode = LineDataSet.Mode.CUBIC_BEZIER
            color = getColor(context)
            highLightColor = getColor(context)
            fillDrawable = getBackground(context)
            lineWidth = 2f
            setDrawFilled(true)
            setDrawCircles(false)
        }

    fun getChangeStatusIcon(): Int =
        if (marketInformation.changeStatus == MarketInformationChangeStatus.POSITIVE) {
            R.drawable.ic_arrow_positive
        } else {
            R.drawable.ic_arrow_negative
        }

    fun isChip1dChecked() = marketInformation.timespan == MarketInformationTimespan.TIMESPAN_1DAYS

    fun isChip7dChecked() = marketInformation.timespan == MarketInformationTimespan.TIMESPAN_7DAYS

    fun isChip30dChecked() = marketInformation.timespan == MarketInformationTimespan.TIMESPAN_30DAYS

    fun isChip60dChecked() = marketInformation.timespan == MarketInformationTimespan.TIMESPAN_60DAYS

    fun isChip90dChecked() = marketInformation.timespan == MarketInformationTimespan.TIMESPAN_90DAYS

    fun isChip1yChecked() = marketInformation.timespan == MarketInformationTimespan.TIMESPAN_1YEAR

    @VisibleForTesting
    fun getColor(context: Context) =
        if (marketInformation.changeStatus == MarketInformationChangeStatus.POSITIVE) {
            context.getCompatColor(R.color.green_500)
        } else {
            context.getCompatColor(R.color.red_500)
        }

    @VisibleForTesting
    fun getBackground(context: Context) =
        if (marketInformation.changeStatus == MarketInformationChangeStatus.POSITIVE) {
            context.getCompatDrawable(R.drawable.background_positive_chart)
        } else {
            context.getCompatDrawable(R.drawable.background_negative_chart)
        }
}
