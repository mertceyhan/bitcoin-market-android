package com.mertceyhan.bitcoinmarket.features.market.ui

import android.content.Context
import androidx.annotation.VisibleForTesting
import com.github.mikephil.charting.data.LineDataSet
import com.mertceyhan.R
import com.mertceyhan.bitcoinmarket.components.TimeRange
import com.mertceyhan.bitcoinmarket.features.market.domain.model.MarketInformation
import com.mertceyhan.bitcoinmarket.features.market.domain.model.MarketInformationChangeStatus
import com.mertceyhan.bitcoinmarket.features.market.domain.model.MarketInformationTimespan
import com.mertceyhan.bitcoinmarket.utils.extensions.getCompatColor
import com.mertceyhan.bitcoinmarket.utils.extensions.getCompatDrawable

class MarketScreenViewState(
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

    fun getTimeRange() = when (marketInformation.timespan) {
        MarketInformationTimespan.TIMESPAN_1DAYS -> TimeRange.ONE_DAY
        MarketInformationTimespan.TIMESPAN_7DAYS -> TimeRange.SEVEN_DAYS
        MarketInformationTimespan.TIMESPAN_30DAYS -> TimeRange.THIRTY_DAYS
        MarketInformationTimespan.TIMESPAN_60DAYS -> TimeRange.SIXTY_DAYS
        MarketInformationTimespan.TIMESPAN_90DAYS -> TimeRange.NINETY_DAYS
        MarketInformationTimespan.TIMESPAN_1YEAR -> TimeRange.ONE_YEAR
    }

    fun isChangeStatusPositive() =
        marketInformation.changeStatus == MarketInformationChangeStatus.POSITIVE

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
