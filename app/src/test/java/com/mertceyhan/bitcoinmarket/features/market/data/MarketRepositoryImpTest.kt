package com.mertceyhan.bitcoinmarket.features.market.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mertceyhan.bitcoinmarket.features.market.data.local.MarketLocalDataSource
import com.mertceyhan.bitcoinmarket.features.market.data.local.PreferencesDataSource
import com.mertceyhan.bitcoinmarket.features.market.data.local.model.MarketPriceChartEntity
import com.mertceyhan.bitcoinmarket.features.market.data.remote.MarketRemoteDataSource
import com.mertceyhan.bitcoinmarket.features.market.data.remote.respose.MarketPriceChartResponse
import com.mertceyhan.bitcoinmarket.features.market.data.remote.respose.MarketPriceChartResponseFactory
import com.mertceyhan.bitcoinmarket.features.market.domain.model.MarketInformationTimespan
import com.mertceyhan.bitcoinmarket.utils.`should be`
import io.mockk.*
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
    private val marketLocalDataSource = mockk<MarketLocalDataSource>()
    private val preferencesDataSource = mockk<PreferencesDataSource>()
    private val marketPriceChartMapper = mockk<MarketPriceChartMapper>()

    @Before
    fun setUp() {
        marketRepositoryImp = MarketRepositoryImp(
            ioDispatcher = ioDispatcher,
            marketRemoteDataSource = marketRemoteDataSource,
            marketLocalDataSource = marketLocalDataSource,
            preferencesDataSource = preferencesDataSource,
            marketPriceChartMapper = marketPriceChartMapper
        )
    }

    @Test
    fun `check fetchMarketPriceChart() success case fetch from remote when isDataExpired is true`() =
        runBlocking {
            // given
            val timespan = MarketInformationTimespan.TIMESPAN_1YEAR
            val marketPriceChartResponse =
                MarketPriceChartResponseFactory.getMockMarketPriceChartResponse()

            every { preferencesDataSource.getLastMarketRequestTime(any()) } returns 1L
            every { marketPriceChartMapper.mapToEntity(any(), any()) } returns mockk()
            coEvery { marketLocalDataSource.insertMarketPriceChart(any()) } just Runs
            coEvery { preferencesDataSource.setLastMarketRequestTime(any()) } just Runs
            coEvery {
                marketRemoteDataSource.fetchMarketPriceChart(timespan.value)
            } returns (MarketPriceChartResponseFactory.getMockMarketPriceChartResponse())

            // when
            val result = marketRepositoryImp.fetchMarketPriceChart(timespan.value)

            // then
            result `should be` marketPriceChartResponse
            coVerify(exactly = 1) { preferencesDataSource.getLastMarketRequestTime(any()) }
            coVerify(exactly = 1) { marketRemoteDataSource.fetchMarketPriceChart(timespan.value) }
            coVerify(exactly = 1) { marketLocalDataSource.insertMarketPriceChart(any()) }
            coVerify(exactly = 1) { preferencesDataSource.setLastMarketRequestTime(any()) }
            coVerify(exactly = 0) { marketLocalDataSource.fetchMarketPriceChart(any()) }
        }

    @Test
    fun `check fetchMarketPriceChart() success case fetch from local when isDataExpired is false`() =
        runBlocking {
            // given
            val timespan = MarketInformationTimespan.TIMESPAN_1YEAR
            val marketPriceChartResponse =
                MarketPriceChartResponseFactory.getMockMarketPriceChartResponse()

            every { preferencesDataSource.getLastMarketRequestTime(any()) } returns 100000000000000000L
            coEvery { marketLocalDataSource.fetchMarketPriceChart(timespan.value) } returns mockk()
            every { marketPriceChartMapper.mapToResponse(any()) } returns marketPriceChartResponse

            // when
            val result = marketRepositoryImp.fetchMarketPriceChart(timespan.value)

            // then
            result `should be` marketPriceChartResponse
            coVerify(exactly = 1) { preferencesDataSource.getLastMarketRequestTime(any()) }
            coVerify(exactly = 1) { marketLocalDataSource.fetchMarketPriceChart(any()) }
            coVerify(exactly = 1) { marketPriceChartMapper.mapToResponse(any()) }
            coVerify(exactly = 0) { marketLocalDataSource.insertMarketPriceChart(any()) }
            coVerify(exactly = 0) { preferencesDataSource.setLastMarketRequestTime(any()) }
            coVerify(exactly = 0) { marketRemoteDataSource.fetchMarketPriceChart(timespan.value) }
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
