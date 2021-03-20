package com.mertceyhan.bitcoinmarket.features.market.domain.mapper

import com.mertceyhan.bitcoinmarket.features.market.data.remote.respose.MarketPriceChartResponse
import com.mertceyhan.bitcoinmarket.features.market.data.remote.respose.MarketPriceValueResponse
import com.mertceyhan.bitcoinmarket.features.market.domain.model.MarketInformationChangeStatus
import com.mertceyhan.bitcoinmarket.features.market.domain.model.MarketInformationTimespan
import com.mertceyhan.bitcoinmarket.utils.`should be`
import com.mertceyhan.bitcoinmarket.utils.extensions.changeRateOf
import com.mertceyhan.bitcoinmarket.utils.extensions.toCurrency
import org.junit.Before
import org.junit.Test

class MarketInformationMapperTest {

    lateinit var marketInformationMapper: MarketInformationMapper

    @Before
    fun setUp() {
        marketInformationMapper = MarketInformationMapper()
    }

    @Test
    fun `check mapOnMarketPriceChartResponse()`() {
        // given
        val marketPriceChartResponse = MarketPriceChartResponse(
            description = "",
            name = "",
            period = "",
            status = "",
            values = listOf(MarketPriceValueResponse(1, 1.0), MarketPriceValueResponse(1, 2.0)),
            unit = ""
        )
        val timespan = MarketInformationTimespan.TIMESPAN_1DAYS

        // when
        val result = marketInformationMapper.mapOnMarketPriceChartResponse(
            marketPriceChartResponse,
            timespan
        )

        // then
        val prices = marketPriceChartResponse.values.map { it.price }

        result.currentPrice `should be` prices.last().toCurrency()
        result.openPrice `should be` prices.first().toCurrency()
        result.closePrice `should be` prices.last().toCurrency()
        result.highPrice `should be` prices.maxOrNull().toCurrency()
        result.lowPrice `should be` prices.minOrNull().toCurrency()
        result.averagePrice `should be` prices.average().toCurrency()
        result.changePrice `should be` (prices.last().minus(prices.first())).toCurrency()
        result.changeRate `should be` "${prices.firstOrNull()?.changeRateOf(prices.last())}%"
        result.aboutChart `should be` marketPriceChartResponse.description
        result.timespan `should be` timespan
        result.chartEntries.size `should be` prices.size
    }

    @Test
    fun `check mapOnMarketPriceValueResponse()`() {
        // given
        val timestamp = 1
        val price = 1.0
        val marketPriceValueResponse = MarketPriceValueResponse(timestamp, price)

        // when
        val result = marketInformationMapper.mapOnMarketPriceValueResponse(marketPriceValueResponse)

        // then
        result.x `should be` timestamp.toFloat()
        result.y `should be` price.toFloat()
    }

    @Test
    fun `when lastPrice is greater than firstPrice last, then getChangeStatus returns POSITIVE`() {
        // given
        val firstPrice = 1.0
        val lastPrice = 3.0

        // when
        val result = marketInformationMapper.getChangeStatus(firstPrice, lastPrice)

        // then
        result `should be` MarketInformationChangeStatus.POSITIVE
    }

    @Test
    fun `when lastPrice is equal to firstPrice than last, then getChangeStatus returns POSITIVE`() {
        // given
        val firstPrice = 1.0
        val lastPrice = 1.0

        // when
        val result = marketInformationMapper.getChangeStatus(firstPrice, lastPrice)

        // then
        result `should be` MarketInformationChangeStatus.POSITIVE
    }

    @Test
    fun `when lastPrice is not greater firstPrice than last, then getChangeStatus returns NEGATIVE`() {
        // given
        val firstPrice = 3.0
        val lastPrice = 1.0

        // when
        val result = marketInformationMapper.getChangeStatus(firstPrice, lastPrice)

        // then
        result `should be` MarketInformationChangeStatus.NEGATIVE
    }
}
