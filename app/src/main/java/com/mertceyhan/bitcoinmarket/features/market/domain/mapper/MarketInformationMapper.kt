package com.mertceyhan.bitcoinmarket.features.market.domain.mapper

import androidx.annotation.VisibleForTesting
import com.github.mikephil.charting.data.Entry
import com.mertceyhan.bitcoinmarket.features.market.data.remote.respose.MarketPriceChartResponse
import com.mertceyhan.bitcoinmarket.features.market.data.remote.respose.MarketPriceValueResponse
import com.mertceyhan.bitcoinmarket.features.market.domain.model.MarketInformation
import com.mertceyhan.bitcoinmarket.features.market.domain.model.MarketInformationChangeStatus
import com.mertceyhan.bitcoinmarket.features.market.domain.model.MarketInformationTimespan
import com.mertceyhan.bitcoinmarket.utils.extensions.changeRateOf
import com.mertceyhan.bitcoinmarket.utils.extensions.orZero
import com.mertceyhan.bitcoinmarket.utils.extensions.toCurrency
import javax.inject.Inject

class MarketInformationMapper @Inject constructor() {

    fun mapOnMarketPriceChartResponse(
        marketPriceChartResponse: MarketPriceChartResponse,
        timespan: MarketInformationTimespan
    ): MarketInformation {
        val prices = marketPriceChartResponse.values.map { it.price }
        val firstPrice = prices.firstOrNull().orZero()
        val lastPrice = prices.lastOrNull().orZero()

        return MarketInformation(
            currentPrice = lastPrice.toCurrency(),
            openPrice = firstPrice.toCurrency(),
            closePrice = lastPrice.toCurrency(),
            highPrice = prices.maxOrNull().toCurrency(),
            lowPrice = prices.minOrNull().toCurrency(),
            averagePrice = prices.average().toCurrency(),
            changePrice = (lastPrice.minus(firstPrice)).toCurrency(),
            changeRate = "${firstPrice.changeRateOf(lastPrice)}%",
            changeStatus = getChangeStatus(firstPrice, lastPrice),
            aboutChart = marketPriceChartResponse.description,
            timespan = timespan,
            chartEntries = marketPriceChartResponse.values.map { mapOnMarketPriceValueResponse(it) }
        )
    }

    @VisibleForTesting
    fun mapOnMarketPriceValueResponse(marketPriceValueResponse: MarketPriceValueResponse) =
        Entry(
            marketPriceValueResponse.timestamp.toFloat(),
            marketPriceValueResponse.price.toFloat()
        )

    @VisibleForTesting
    fun getChangeStatus(firstPrice: Double, lastPrice: Double) =
        if (lastPrice >= firstPrice) {
            MarketInformationChangeStatus.POSITIVE
        } else {
            MarketInformationChangeStatus.NEGATIVE
        }
}
