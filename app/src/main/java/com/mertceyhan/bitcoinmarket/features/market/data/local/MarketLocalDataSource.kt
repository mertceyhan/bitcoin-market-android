package com.mertceyhan.bitcoinmarket.features.market.data.local

import com.mertceyhan.bitcoinmarket.features.market.data.local.entity.MarketPriceChartEntity
import javax.inject.Inject

class MarketLocalDataSource @Inject constructor(
    private val marketDao: MarketDao
) {

    suspend fun insertMarketPriceChart(marketPriceChartEntity: MarketPriceChartEntity) {
        marketDao.insertMarketPriceChart(marketPriceChartEntity)
    }

    suspend fun fetchMarketPriceChart(timeSpan: String): MarketPriceChartEntity? =
        marketDao.fetchMarketPriceChart(timeSpan)
}
