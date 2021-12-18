package com.mertceyhan.bitcoinmarket.features.market.data

import com.mertceyhan.bitcoinmarket.features.market.data.remote.respose.MarketPriceChartResponse

interface MarketRepository {

    suspend fun fetchMarketPriceChart(timespan: String): MarketPriceChartResponse

    fun isDataExpired(timeSpan: String): Boolean
}
