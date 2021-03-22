package com.mertceyhan.bitcoinmarket.features.market.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.mertceyhan.bitcoinmarket.base.data.State
import com.mertceyhan.bitcoinmarket.features.market.data.remote.MarketRemoteDataSource
import com.mertceyhan.bitcoinmarket.features.market.data.remote.respose.MarketPriceChartResponseFactory
import com.mertceyhan.bitcoinmarket.features.market.domain.model.MarketInformationTimespan
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.net.SocketTimeoutException

class MarketRepositoryImpTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var marketRepositoryImp: MarketRepositoryImp

    private val marketRemoteDataSource = mockk<MarketRemoteDataSource>()

    @Before
    fun setUp() {
        marketRepositoryImp = MarketRepositoryImp(marketRemoteDataSource)
    }

    @Test
    fun `check fetchMarketPriceChart() success case`() =
        runBlocking {
            // given
            val timespan = MarketInformationTimespan.TIMESPAN_1YEAR
            val marketPriceChartResponse =
                MarketPriceChartResponseFactory.getMockMarketPriceChartResponse()

            coEvery {
                marketRemoteDataSource.fetchMarketPriceChart(timespan.value)
            } returns MarketPriceChartResponseFactory.getMockMarketPriceChartResponse()

            // when
            val result = marketRepositoryImp
                .fetchMarketPriceChart(timespan.value)
                .toList()

            // then
            assertThat(result[0]).isSameInstanceAs(State.Loading)
            assertThat(result[1]).isInstanceOf(State.Success::class.java)
            assertThat((result[1] as State.Success).data).isEqualTo(marketPriceChartResponse)
            assertThat(result.size).isEqualTo(2)

            coVerify(exactly = 1) { marketRemoteDataSource.fetchMarketPriceChart(timespan.value) }
        }

    @Test
    fun `check fetchMarketPriceChart() error case`() =
        runBlocking {
            // given
            val timespan = MarketInformationTimespan.TIMESPAN_1YEAR
            val socketTimeoutException = SocketTimeoutException()

            coEvery {
                marketRemoteDataSource.fetchMarketPriceChart(timespan.value)
            } throws socketTimeoutException

            // when
            val result = marketRepositoryImp
                .fetchMarketPriceChart(timespan.value)
                .toList()

            // then
            assertThat(result[0]).isSameInstanceAs(State.Loading)
            assertThat(result[1]).isInstanceOf(State.Error::class.java)
            assertThat((result[1] as State.Error).exception).isEqualTo(socketTimeoutException)
            assertThat(result.size).isEqualTo(2)

            coVerify(exactly = 1) { marketRemoteDataSource.fetchMarketPriceChart(timespan.value) }
        }
}
