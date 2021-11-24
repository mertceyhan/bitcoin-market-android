package com.mertceyhan.bitcoinmarket.features.market.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mertceyhan.bitcoinmarket.features.market.data.remote.MarketRemoteDataSource
import com.mertceyhan.bitcoinmarket.features.market.data.remote.respose.MarketPriceChartResponse
import com.mertceyhan.bitcoinmarket.features.market.data.remote.respose.MarketPriceChartResponseFactory
import com.mertceyhan.bitcoinmarket.features.market.domain.model.MarketInformationTimespan
import com.mertceyhan.bitcoinmarket.utils.`should be`
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.net.SocketTimeoutException

@ExperimentalCoroutinesApi
class MarketRepositoryImpTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var marketRepositoryImp: MarketRepositoryImp

    private val ioDispatcher = TestCoroutineDispatcher()

    private val marketRemoteDataSource = mockk<MarketRemoteDataSource>()

    @Before
    fun setUp() {
        marketRepositoryImp = MarketRepositoryImp(ioDispatcher, marketRemoteDataSource)
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
            } returns (MarketPriceChartResponseFactory.getMockMarketPriceChartResponse())

            // when
            val result = marketRepositoryImp.fetchMarketPriceChart(timespan.value)

            // then
            result `should be` marketPriceChartResponse

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
            var result: MarketPriceChartResponse? = null

            try {
                result = marketRepositoryImp.fetchMarketPriceChart(timespan.value)
            } catch (exception: Exception) {
                exception `should be` socketTimeoutException
            }

            // then
            result `should be` null

            coVerify(exactly = 1) { marketRemoteDataSource.fetchMarketPriceChart(timespan.value) }
        }
}
