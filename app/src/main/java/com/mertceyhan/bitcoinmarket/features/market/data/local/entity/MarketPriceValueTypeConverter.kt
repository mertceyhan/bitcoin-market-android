package com.mertceyhan.bitcoinmarket.features.market.data.local.entity

import androidx.room.TypeConverter
import com.mertceyhan.bitcoinmarket.features.market.data.remote.respose.MarketPriceValueResponse
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class MarketPriceValueTypeConverter {

    private val moshi = Moshi.Builder().build()
    private val type = Types.newParameterizedType(
        List::class.java,
        MarketPriceValueResponse::class.java
    )
    private val adapter: JsonAdapter<List<MarketPriceValueResponse>> = moshi.adapter(type)

    @TypeConverter
    fun fromString(data: String): List<MarketPriceValueResponse>? = adapter.fromJson(data)

    @TypeConverter
    fun toString(data: List<MarketPriceValueResponse>?): String = adapter.toJson(data)

}