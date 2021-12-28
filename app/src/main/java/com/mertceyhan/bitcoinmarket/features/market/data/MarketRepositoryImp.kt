package com.mertceyhan.bitcoinmarket.features.market.data

import androidx.annotation.VisibleForTesting
import com.mertceyhan.bitcoinmarket.core.di.IoDispatcher
import com.mertceyhan.bitcoinmarket.features.market.data.local.MarketLocalDataSource
import com.mertceyhan.bitcoinmarket.features.market.data.local.model.MarketPriceChartEntity
import com.mertceyhan.bitcoinmarket.features.market.data.remote.MarketRemoteDataSource
import com.mertceyhan.bitcoinmarket.features.market.data.remote.respose.MarketPriceChartResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MarketRepositoryImp @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val marketRemoteDataSource: MarketRemoteDataSource,
    private val marketLocalDataSource: MarketLocalDataSource,
    private val marketPriceChartMapper: MarketPriceChartMapper,
) : MarketRepository {

    override suspend fun fetchMarketPriceChart(timespan: String) = withContext(ioDispatcher) {

        fetchMarketDataFromLocal(timespan)?.takeIf { !it.isExpired() }
            ?.let { marketPriceChartMapper.mapToResponse(it) }
            ?: run {
                val marketRemoteData = fetchMarketDataFromRemote(timespan)

                fetchMarketDataFromLocal(timespan)
                    ?.let { marketPriceChartMapper.mapToResponse(it) }
                    ?: marketRemoteData
            }
    }


    @VisibleForTesting
    fun MarketPriceChartEntity.isExpired(): Boolean =
        System.currentTimeMillis() - lastFetchTime > EXPIRED_TIME

    @VisibleForTesting
    suspend fun fetchMarketDataFromLocal(timeSpan: String) =
        marketLocalDataSource.fetchMarketPriceChart(timeSpan)

    @VisibleForTesting
    suspend fun fetchMarketDataFromRemote(timeSpan: String) =
        marketRemoteDataSource.fetchMarketPriceChart(timeSpan)
            .also { marketRemoteData ->
                insertMarketResponse(
                    timeSpan = timeSpan,
                    remoteData = marketRemoteData
                )
            }

    @VisibleForTesting
    suspend fun insertMarketResponse(timeSpan: String, remoteData: MarketPriceChartResponse) {
        marketLocalDataSource.insertMarketPriceChart(
            marketPriceChartMapper.mapToEntity(
                timeSpan = timeSpan,
                lastFetchTime = System.currentTimeMillis(),
                response = remoteData
            )
        )
    }

    companion object {
        private const val EXPIRED_TIME = 1000L * 60
    }
}
