package com.mertceyhan.bitcoinmarket.features.market.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mertceyhan.bitcoinmarket.features.market.data.MarketRepository
import com.mertceyhan.bitcoinmarket.features.market.data.remote.respose.MarketPriceChartResponseFactory
import com.mertceyhan.bitcoinmarket.features.market.domain.mapper.MarketInformationMapper
import com.mertceyhan.bitcoinmarket.features.market.domain.model.MarketInformation
import com.mertceyhan.bitcoinmarket.features.market.domain.model.MarketInformationFactory
import com.mertceyhan.bitcoinmarket.features.market.domain.model.MarketInformationTimespan
import com.mertceyhan.bitcoinmarket.utils.`should be`
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

class MarketInformationUseCaseImplTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var marketInformationUseCaseImpl: MarketInformationUseCaseImpl

    private val marketRepository = mockk<MarketRepository>()

    private val marketInformationMapper = mockk<MarketInformationMapper>()

    @Before
    fun setUp() {
        marketInformationUseCaseImpl = MarketInformationUseCaseImpl(
            marketRepository, marketInformationMapper
        )
    }

    @Test
    fun `check getMarketInformation() success case`() =
        runBlocking {
            // given
            val timespan = MarketInformationTimespan.TIMESPAN_1YEAR
            val marketInformation = MarketInformationFactory.getMockMarketInformation()
            val marketPriceChartResponse =
                MarketPriceChartResponseFactory.getMockMarketPriceChartResponse()

            coEvery {
                marketRepository.fetchMarketPriceChart(timespan.value)
            } returns (marketPriceChartResponse)

            coEvery {
                marketInformationMapper.mapOnMarketPriceChartResponse(
                    marketPriceChartResponse,
                    timespan
                )
            } returns marketInformation

            // when
            val result = marketInformationUseCaseImpl.getMarketInformation(timespan)

            // then
            result `should be` marketInformation

            coVerify(exactly = 1) { marketRepository.fetchMarketPriceChart(timespan.value) }
            coVerify(exactly = 1) {
                marketInformationMapper.mapOnMarketPriceChartResponse(
                    marketPriceChartResponse,
                    timespan
                )
            }
        }

    @Test
    fun `check getMarketInformation() error case`() =
        runBlocking {
            // given
            val timespan = MarketInformationTimespan.TIMESPAN_1YEAR
            val error = IOException()
            val marketPriceChartResponse =
                MarketPriceChartResponseFactory.getMockMarketPriceChartResponse()

            coEvery {
                marketRepository.fetchMarketPriceChart(timespan.value)
            } throws (error)

            // when
            var result: MarketInformation? = null

            try {
                result = marketInformationUseCaseImpl.getMarketInformation(timespan)
            } catch (exception: Exception) {
                exception `should be` error
            }

            // then
            result `should be` null

            coVerify(exactly = 1) { marketRepository.fetchMarketPriceChart(timespan.value) }
            coVerify(exactly = 0) {
                marketInformationMapper.mapOnMarketPriceChartResponse(
                    marketPriceChartResponse,
                    timespan
                )
            }

        }
}
