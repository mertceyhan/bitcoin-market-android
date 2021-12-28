package com.mertceyhan.bitcoinmarket.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mertceyhan.bitcoinmarket.core.data.local.MarketDatabase.Companion.VERSION
import com.mertceyhan.bitcoinmarket.features.market.data.local.MarketDao
import com.mertceyhan.bitcoinmarket.features.market.data.local.model.MarketPriceChartEntity
import com.mertceyhan.bitcoinmarket.features.market.data.local.model.MarketPriceValueTypeConverter

@Database(
    entities = [MarketPriceChartEntity::class],
    version = VERSION,
    exportSchema = false
)
@TypeConverters(MarketPriceValueTypeConverter::class)
abstract class MarketDatabase : RoomDatabase() {

    abstract fun getMarketDao(): MarketDao

    companion object {
        const val VERSION = 1
        const val DB_NAME = "MarketDatabase.db"
    }
}