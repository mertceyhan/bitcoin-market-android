package com.mertceyhan.bitcoinmarket.features.market.data.remote

import com.mertceyhan.bitcoinmarket.features.market.data.remote.respose.MarketPriceChartResponse
import javax.inject.Inject

class MarketRemoteDataSource @Inject constructor(
    private val marketService: MarketService
) {

    suspend fun fetchMarketPriceChart(timespan: String): MarketPriceChartResponse =
        marketService.fetchMarketPriceChart(timespan)
}
