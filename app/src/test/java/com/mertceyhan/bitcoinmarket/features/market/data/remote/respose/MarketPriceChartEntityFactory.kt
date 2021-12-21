package com.mertceyhan.bitcoinmarket.features.market.data.remote.respose

import com.mertceyhan.bitcoinmarket.features.market.data.local.model.MarketPriceChartEntity

object MarketPriceChartEntityFactory {

    fun getMockNotExpiredMarketPriceChartEntity(
        timeSpan: String = "",
    ) = MarketPriceChartEntity(
        timeSpan = timeSpan,
        lastFetchTime = System.currentTimeMillis(),
        description = "",
        name = "",
        period = "",
        status = "",
        unit = "",
        values = arrayListOf()
    )

    fun getMockExpiredMarketPriceChartEntity(
        timeSpan: String = "",
    ) = MarketPriceChartEntity(
        timeSpan = timeSpan,
        lastFetchTime = System.currentTimeMillis() - 1000 * 60,
        description = "",
        name = "",
        period = "",
        status = "",
        unit = "",
        values = arrayListOf()
    )
}
