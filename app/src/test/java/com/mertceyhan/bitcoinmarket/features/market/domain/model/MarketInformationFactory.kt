package com.mertceyhan.bitcoinmarket.features.market.domain.model

import com.github.mikephil.charting.data.Entry

object MarketInformationFactory {

    fun getMockMarketInformation() = MarketInformation(
        currentPrice = "",
        openPrice = "",
        closePrice = "",
        highPrice = "",
        lowPrice = "",
        averagePrice = "",
        changePrice = "",
        changeRate = "",
        changeStatus = MarketInformationChangeStatus.POSITIVE,
        aboutChart = "",
        timespan = MarketInformationTimespan.TIMESPAN_1DAYS,
        chartEntries = listOf(Entry(1f, 1f))
    )
}
