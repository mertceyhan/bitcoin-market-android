package com.mertceyhan.bitcoinmarket.features.market.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mertceyhan.bitcoinmarket.features.market.data.remote.respose.MarketPriceValueResponse
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity
@JsonClass(generateAdapter = true)
data class MarketPriceChartEntity(

    @PrimaryKey
    @field:Json(name = "timespan")
    val timeSpan: String,
    @field:Json(name = "description")
    val description: String,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "period")
    val period: String,
    @field:Json(name = "status")
    val status: String,
    @field:Json(name = "unit")
    val unit: String,
    @field:Json(name = "values")
    val values: List<MarketPriceValueResponse>
)
