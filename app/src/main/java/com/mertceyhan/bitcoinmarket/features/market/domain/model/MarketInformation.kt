package com.mertceyhan.bitcoinmarket.features.market.domain.model

import com.github.mikephil.charting.data.Entry

data class MarketInformation(
    val currentPrice: String,
    val openPrice: String,
    val closePrice: String,
    val highPrice: String,
    val lowPrice: String,
    val averagePrice: String,
    val changePrice: String,
    val changeRate: String,
    val changeStatus: MarketInformationChangeStatus,
    val aboutChart: String,
    val timespan: MarketInformationTimespan,
    val chartEntries: List<Entry>
)
