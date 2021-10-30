package com.mertceyhan.bitcoinmarket.features.market.data

import com.mertceyhan.bitcoinmarket.features.market.data.remote.MarketRemoteDataSource
import javax.inject.Inject

class MarketRepositoryImp @Inject constructor(
    private val marketRemoteDataSource: MarketRemoteDataSource
) : MarketRepository {

    override suspend fun fetchMarketPriceChart(timespan: String) =
        marketRemoteDataSource.fetchMarketPriceChart(timespan)
}
