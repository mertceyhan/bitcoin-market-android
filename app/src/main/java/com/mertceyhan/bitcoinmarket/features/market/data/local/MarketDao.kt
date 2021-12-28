package com.mertceyhan.bitcoinmarket.features.market.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mertceyhan.bitcoinmarket.features.market.data.local.model.MarketPriceChartEntity

@Dao
interface MarketDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMarketPriceChart(marketPriceChartEntity: MarketPriceChartEntity)

    @Query("SELECT * FROM MarketPriceChartEntity WHERE timeSpan = :timeSpan")
    suspend fun fetchMarketPriceChart(timeSpan: String): MarketPriceChartEntity?
}