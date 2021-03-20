package com.mertceyhan.bitcoinmarket.features.market.data.remote

import com.mertceyhan.bitcoinmarket.features.market.data.remote.respose.MarketPriceChartResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MarketService {

    @GET("charts/market-price")
    suspend fun fetchMarketPriceChart(@Query("timespan") timespan: String): MarketPriceChartResponse
}
