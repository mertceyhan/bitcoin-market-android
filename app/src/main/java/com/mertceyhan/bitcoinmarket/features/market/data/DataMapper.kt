package com.mertceyhan.bitcoinmarket.features.market.data

import com.mertceyhan.bitcoinmarket.features.market.data.local.entity.MarketPriceChartEntity
import com.mertceyhan.bitcoinmarket.features.market.data.remote.respose.MarketPriceChartResponse
import javax.inject.Inject

class DataMapper @Inject constructor() {

    fun mapToEntity(timeSpan: String, response: MarketPriceChartResponse): MarketPriceChartEntity =
        with(response) {
            MarketPriceChartEntity(
                timeSpan = timeSpan,
                description = description,
                name = name,
                period = period,
                status = status,
                unit = unit,
                values = values
            )
        }

    fun mapToResponse(entity: MarketPriceChartEntity): MarketPriceChartResponse = with(entity) {
        MarketPriceChartResponse(
            description = description,
            name = name,
            period = period,
            status = status,
            unit = unit,
            values = values
        )
    }
}
