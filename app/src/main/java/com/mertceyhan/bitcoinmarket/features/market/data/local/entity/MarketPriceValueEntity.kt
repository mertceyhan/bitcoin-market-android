package com.mertceyhan.bitcoinmarket.features.market.data.local.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MarketPriceValueEntity(

    @field:Json(name = "x")
    val timestamp: Int,
    @field:Json(name = "y")
    val price: Double
)
