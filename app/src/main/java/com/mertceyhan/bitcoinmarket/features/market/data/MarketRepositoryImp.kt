package com.mertceyhan.bitcoinmarket.features.market.data

import com.mertceyhan.bitcoinmarket.core.di.IoDispatcher
import com.mertceyhan.bitcoinmarket.features.market.data.remote.MarketRemoteDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MarketRepositoryImp @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val marketRemoteDataSource: MarketRemoteDataSource
) : MarketRepository {

    override suspend fun fetchMarketPriceChart(timespan: String) = withContext(ioDispatcher) {
        marketRemoteDataSource.fetchMarketPriceChart(timespan)
    }
}
