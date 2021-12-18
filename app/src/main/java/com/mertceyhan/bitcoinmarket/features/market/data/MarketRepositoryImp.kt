package com.mertceyhan.bitcoinmarket.features.market.data

import com.mertceyhan.bitcoinmarket.core.di.IoDispatcher
import com.mertceyhan.bitcoinmarket.features.market.data.local.MarketLocalDataSource
import com.mertceyhan.bitcoinmarket.features.market.data.local.PreferencesDataSource
import com.mertceyhan.bitcoinmarket.features.market.data.remote.MarketRemoteDataSource
import com.mertceyhan.bitcoinmarket.features.market.data.remote.respose.MarketPriceChartResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MarketRepositoryImp @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val marketRemoteDataSource: MarketRemoteDataSource,
    private val marketLocalDataSource: MarketLocalDataSource,
    private val preferencesDataSource: PreferencesDataSource,
    private val dataMapper: DataMapper
) : MarketRepository {

    override suspend fun fetchMarketPriceChart(timespan: String) = withContext(ioDispatcher) {
        if (isDataExpired(timespan)) {
            fetchDataFromRemote(timespan)
        } else {
            fetchDataFromLocal(timespan)
        }
    }

    private suspend fun fetchDataFromRemote(timeSpan: String): MarketPriceChartResponse =
        marketRemoteDataSource.fetchMarketPriceChart(timeSpan).also { response ->
            insertMarketPriceChart(
                timeSpan = timeSpan,
                marketPriceChartResponse = response
            )
        }

    private suspend fun fetchDataFromLocal(timeSpan: String): MarketPriceChartResponse =
        marketLocalDataSource.fetchMarketPriceChart(timeSpan)?.let { marketEntity ->
            dataMapper.mapToResponse(marketEntity)
        } ?: fetchDataFromRemote(timeSpan)


    private suspend fun insertMarketPriceChart(
        timeSpan: String,
        marketPriceChartResponse: MarketPriceChartResponse
    ) = marketLocalDataSource.insertMarketPriceChart(
        dataMapper.mapToEntity(
            timeSpan = timeSpan,
            response = marketPriceChartResponse
        )
    ).also { preferencesDataSource.setLastMarketRequestTime(timeSpan) }

    override fun isDataExpired(timeSpan: String): Boolean =
        System.currentTimeMillis() - preferencesDataSource.getLastMarketRequestTime(timeSpan) > EXPIRED_TIME

    companion object {
        private const val EXPIRED_TIME = 1000L * 60
    }

}
