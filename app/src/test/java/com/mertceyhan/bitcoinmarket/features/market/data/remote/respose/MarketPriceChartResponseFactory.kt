package com.mertceyhan.bitcoinmarket.features.market.data.remote.respose

object MarketPriceChartResponseFactory {

    fun getMockMarketPriceChartResponse() = MarketPriceChartResponse(
        description = "",
        name = "",
        period = "",
        status = "",
        values = listOf(MarketPriceValueResponse(1, 1.0)),
        unit = ""
    )
}
