package com.mertceyhan.bitcoinmarket.features.market.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mertceyhan.bitcoinmarket.features.market.data.local.MarketLocalDataSource
import com.mertceyhan.bitcoinmarket.features.market.data.remote.MarketRemoteDataSource
import com.mertceyhan.bitcoinmarket.features.market.data.remote.respose.MarketPriceChartEntityFactory
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

@ExperimentalCoroutinesApi
class MarketRepositoryImpTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var marketRepositoryImp: MarketRepositoryImp

    private val ioDispatcher = TestCoroutineDispatcher()

    private val marketRemoteDataSource = mockk<MarketRemoteDataSource>()
    private val marketLocalDataSource = mockk<MarketLocalDataSource>()
    private val marketPriceChartMapper = mockk<MarketPriceChartMapper>()

    @Before
    fun setUp() {
        marketRepositoryImp = MarketRepositoryImp(
            ioDispatcher = ioDispatcher,
            marketRemoteDataSource = marketRemoteDataSource,
            marketLocalDataSource = marketLocalDataSource,
            marketPriceChartMapper = marketPriceChartMapper
        )
    }

    @Test
    fun `check fetchMarketPriceChart() success case fetch from remote when data not expired`() =
        runBlocking {
            // given
            val timespan = MarketInformationTimespan.TIMESPAN_1YEAR.value
            val marketPriceChartResponse =
                MarketPriceChartResponseFactory.getMockMarketPriceChartResponse()
            val marketPriceChartEntity =
                MarketPriceChartEntityFactory.getMockNotExpiredMarketPriceChartEntity()

            coEvery {
                marketLocalDataSource.fetchMarketPriceChart(timespan)
            } returns marketPriceChartEntity

            every {
                marketPriceChartMapper.mapToResponse(any())
            } returns marketPriceChartResponse


            // when
            val result = marketRepositoryImp.fetchMarketPriceChart(timespan)

            // then
            result `should be` marketPriceChartResponse
            coVerify(exactly = 1) { marketLocalDataSource.fetchMarketPriceChart(timespan) }
            coVerify(exactly = 0) { marketRemoteDataSource.fetchMarketPriceChart(any()) }
            coVerify(exactly = 0) { marketLocalDataSource.insertMarketPriceChart(any()) }
            verify(exactly = 1) { marketPriceChartMapper.mapToResponse(marketPriceChartEntity) }
        }

    @Test
    fun `check fetchMarketPriceChart() success case fetch from local when isDataExpired is false`() =
        runBlocking {
            // given
            val timespan = MarketInformationTimespan.TIMESPAN_1YEAR.value
            val marketPriceChartResponse =
                MarketPriceChartResponseFactory.getMockMarketPriceChartResponse()
            val marketPriceChartEntity =
                MarketPriceChartEntityFactory.getMockExpiredMarketPriceChartEntity()

            coEvery {
                marketRemoteDataSource.fetchMarketPriceChart(timespan)
            } returns marketPriceChartResponse

            coEvery {
                marketLocalDataSource.insertMarketPriceChart(any())
            } just Runs

            coEvery {
                marketLocalDataSource.fetchMarketPriceChart(timespan)
            } returns marketPriceChartEntity

            every {
                marketPriceChartMapper.mapToResponse(any())
            } returns marketPriceChartResponse

            every {
                marketPriceChartMapper.mapToEntity(any(), any(), any())
            } returns marketPriceChartEntity

            every { marketPriceChartMapper.mapToResponse(any()) } returns marketPriceChartResponse

            // when
            val result = marketRepositoryImp.fetchMarketPriceChart(timespan)

            // then
            result `should be` marketPriceChartResponse
            coVerify(exactly = 1) { marketRemoteDataSource.fetchMarketPriceChart(timespan) }
            coVerify(exactly = 1) {
                marketLocalDataSource.insertMarketPriceChart(marketPriceChartEntity)
            }
            coVerify(exactly = 2) { marketLocalDataSource.fetchMarketPriceChart(timespan) }
            verify(exactly = 1) { marketPriceChartMapper.mapToResponse(marketPriceChartEntity) }
            verify(exactly = 1) {
                marketPriceChartMapper.mapToEntity(
                    timespan, any(), marketPriceChartResponse)
            }
        }
}
