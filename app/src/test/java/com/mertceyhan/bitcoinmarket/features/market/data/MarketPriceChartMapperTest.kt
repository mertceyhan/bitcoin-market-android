package com.mertceyhan.bitcoinmarket.features.market.data


import com.google.common.truth.Truth.assertThat
import com.mertceyhan.bitcoinmarket.features.market.data.local.model.MarketPriceChartEntity
import com.mertceyhan.bitcoinmarket.features.market.data.remote.respose.MarketPriceChartResponse
import com.mertceyhan.bitcoinmarket.features.market.data.remote.respose.MarketPriceValueResponse
import com.mertceyhan.bitcoinmarket.features.market.domain.model.MarketInformationTimespan
import com.mertceyhan.bitcoinmarket.utils.`should be`
import org.junit.Before
import org.junit.Test

class MarketPriceChartMapperTest {

    lateinit var marketPriceChartMapper: MarketPriceChartMapper

    @Before
    fun setUp() {
        marketPriceChartMapper = MarketPriceChartMapper()
    }

    @Test
    fun `check mapToEntity()`() {
        //given
        val timeSpan = MarketInformationTimespan.TIMESPAN_1DAYS.value
        val values = listOf(MarketPriceValueResponse(1, 1.0), MarketPriceValueResponse(2, 2.0))
        val marketPriceChartResponse = MarketPriceChartResponse(
            description = "",
            name = "",
            period = "",
            status = "",
            values = values,
            unit = ""
        )

        //when
        val result = marketPriceChartMapper.mapToEntity(
            timeSpan = timeSpan,
            response = marketPriceChartResponse
        )

        //then
        assertThat(result).isInstanceOf(MarketPriceChartEntity::class.java)
        result.timeSpan `should be` timeSpan
        result.values `should be` values
    }

    @Test
    fun `check mapToResponse()`() {
        //given
        val timeSpan = MarketInformationTimespan.TIMESPAN_1DAYS.value
        val values = listOf(MarketPriceValueResponse(1, 1.0), MarketPriceValueResponse(2, 2.0))
        val marketPriceChartEntity = MarketPriceChartEntity(
            description = "",
            name = "",
            period = "",
            status = "",
            values = values,
            unit = "",
            timeSpan = timeSpan
        )

        //when
        val result = marketPriceChartMapper.mapToResponse(marketPriceChartEntity)

        //then
        assertThat(result).isInstanceOf(MarketPriceChartResponse::class.java)
        result.values `should be` values
    }
}