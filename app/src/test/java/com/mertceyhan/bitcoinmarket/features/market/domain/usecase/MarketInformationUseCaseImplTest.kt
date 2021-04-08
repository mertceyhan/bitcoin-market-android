package com.mertceyhan.bitcoinmarket.features.market.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.mertceyhan.bitcoinmarket.core.data.State
import com.mertceyhan.bitcoinmarket.features.market.data.MarketRepository
import com.mertceyhan.bitcoinmarket.features.market.data.remote.respose.MarketPriceChartResponseFactory
import com.mertceyhan.bitcoinmarket.features.market.domain.mapper.MarketInformationMapper
import com.mertceyhan.bitcoinmarket.features.market.domain.model.MarketInformationFactory
import com.mertceyhan.bitcoinmarket.features.market.domain.model.MarketInformationTimespan
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
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
            } returns flow {
                emit(State.Loading)
                emit(State.Success(marketPriceChartResponse))
            }

            coEvery {
                marketInformationMapper.mapOnMarketPriceChartResponse(
                    marketPriceChartResponse,
                    timespan
                )
            } returns marketInformation

            // when
            val result = marketInformationUseCaseImpl
                .getMarketInformation(timespan)
                .toList()

            // then
            assertThat(result[0]).isSameInstanceAs(State.Loading)
            assertThat(result[1]).isInstanceOf(State.Success::class.java)
            assertThat((result[1] as State.Success).data).isEqualTo(marketInformation)
            assertThat(result.size).isEqualTo(2)

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
            } returns flow {
                emit(State.Loading)
                emit(State.Error(error))
            }

            // when
            val result = marketInformationUseCaseImpl
                .getMarketInformation(timespan)
                .toList()

            // then
            assertThat(result[0]).isSameInstanceAs(State.Loading)
            assertThat(result[1]).isInstanceOf(State.Error::class.java)
            assertThat((result[1] as State.Error).exception).isEqualTo(error)
            assertThat(result.size).isEqualTo(2)

            coVerify(exactly = 1) { marketRepository.fetchMarketPriceChart(timespan.value) }
            coVerify(exactly = 0) {
                marketInformationMapper.mapOnMarketPriceChartResponse(
                    marketPriceChartResponse,
                    timespan
                )
            }
        }
}
