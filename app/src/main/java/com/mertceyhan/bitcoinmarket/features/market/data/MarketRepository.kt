package com.mertceyhan.bitcoinmarket.features.market.data

import com.mertceyhan.bitcoinmarket.base.data.State
import com.mertceyhan.bitcoinmarket.features.market.data.remote.respose.MarketPriceChartResponse
import kotlinx.coroutines.flow.Flow

interface MarketRepository {

    suspend fun fetchMarketPriceChart(timespan: String): Flow<State<MarketPriceChartResponse>>
}
