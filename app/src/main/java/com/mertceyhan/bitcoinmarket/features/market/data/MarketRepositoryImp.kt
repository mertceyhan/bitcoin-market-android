package com.mertceyhan.bitcoinmarket.features.market.data

import com.mertceyhan.bitcoinmarket.base.data.BaseRepository
import com.mertceyhan.bitcoinmarket.base.data.State
import com.mertceyhan.bitcoinmarket.features.market.data.remote.MarketRemoteDataSource
import com.mertceyhan.bitcoinmarket.features.market.data.remote.respose.MarketPriceChartResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MarketRepositoryImp @Inject constructor(
    private val marketRemoteDataSource: MarketRemoteDataSource
) : MarketRepository, BaseRepository() {

    override suspend fun fetchMarketPriceChart(timespan: String): Flow<State<MarketPriceChartResponse>> =
        apiCall { marketRemoteDataSource.fetchMarketPriceChart(timespan) }
}
