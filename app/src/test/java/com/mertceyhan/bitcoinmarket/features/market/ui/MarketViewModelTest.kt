package com.mertceyhan.bitcoinmarket.features.market.ui

import com.google.common.truth.Truth.assertThat
import com.mertceyhan.bitcoinmarket.components.TimeRange
import com.mertceyhan.bitcoinmarket.core.ui.UiState
import com.mertceyhan.bitcoinmarket.features.market.domain.model.MarketInformationFactory
import com.mertceyhan.bitcoinmarket.features.market.domain.model.MarketInformationTimespan
import com.mertceyhan.bitcoinmarket.features.market.domain.usecase.MarketInformationUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class MarketViewModelTest {

    private val mockUseCase = mockk<MarketInformationUseCase>()

    private lateinit var viewmodel: MarketViewModel

    @Before
    fun setupViewmodel() {
        Dispatchers.setMain(TestCoroutineDispatcher())
        viewmodel = MarketViewModel(mockUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `Initial setup of viewmodel UIState should return Loading`() {
        val currentState = viewmodel.getUiState()
        assertThat(currentState.value is UiState.Loading).isTrue()
    }

    @Test
    fun `Get Market Information from usecase return success`() = runBlockingTest {
        coEvery {
            mockUseCase.getMarketInformation(MarketInformationTimespan.TIMESPAN_1DAYS)
        } returns (MarketInformationFactory.getMockMarketInformation())

        viewmodel.getMarketInformation(TimeRange.ONE_DAY)
        val currentState = viewmodel.getUiState()
        assertThat(currentState.value is UiState.Success).isTrue()
        val data = (currentState.value as UiState.Success).data
        assertThat(data.marketInformation.currentPrice).isEqualTo("")
        assertThat(data.marketInformation.timespan).isEqualTo(MarketInformationTimespan.TIMESPAN_1DAYS)
    }

    @Test
    fun `Get Market Information from usecase return error`() = runBlockingTest {
        coEvery {
            mockUseCase.getMarketInformation(MarketInformationTimespan.TIMESPAN_1DAYS)
        }.throws(Exception("Some random error here"))

        viewmodel.getMarketInformation(TimeRange.ONE_DAY)
        val currentState = viewmodel.getUiState()
        assertThat(currentState.value is UiState.Error).isTrue()
        val data = (currentState.value as UiState.Error).exception
        assertThat(data.message).isEqualTo("Some random error here")
    }

    @Test
    fun `Verify that getMarketInfomation is called with correct parameters - MarketInfomationTimespan 90 days`() {
        viewmodel.getMarketInformation(TimeRange.NINETY_DAYS)
        coVerify { mockUseCase.getMarketInformation(eq(MarketInformationTimespan.TIMESPAN_90DAYS)) }
    }

    @Test
    fun `Verify that getMarketInfomation is called with correct parameters - MarketInfomationTimespan 1 day`() {
        viewmodel.getMarketInformation(TimeRange.ONE_DAY)
        coVerify { mockUseCase.getMarketInformation(eq(MarketInformationTimespan.TIMESPAN_1DAYS)) }
    }

    @Test
    fun `Verify that getMarketInfomation is called with correct parameters - MarketInfomationTimespan 7 days`() {
        viewmodel.getMarketInformation(TimeRange.SEVEN_DAYS)
        coVerify { mockUseCase.getMarketInformation(eq(MarketInformationTimespan.TIMESPAN_7DAYS)) }
    }

    @Test
    fun `Verify that getMarketInfomation is called with correct parameters - MarketInfomationTimespan 30 days`() {
        viewmodel.getMarketInformation(TimeRange.THIRTY_DAYS)
        coVerify { mockUseCase.getMarketInformation(eq(MarketInformationTimespan.TIMESPAN_30DAYS)) }
    }

    @Test
    fun `Verify that getMarketInfomation is called with correct parameters - MarketInfomationTimespan 60 days`() {
        viewmodel.getMarketInformation(TimeRange.SIXTY_DAYS)
        coVerify { mockUseCase.getMarketInformation(eq(MarketInformationTimespan.TIMESPAN_60DAYS)) }
    }

    @Test
    fun `Verify that getMarketInfomation is called with correct parameters - MarketInfomationTimespan 1 year`() {
        viewmodel.getMarketInformation(TimeRange.ONE_YEAR)
        coVerify { mockUseCase.getMarketInformation(eq(MarketInformationTimespan.TIMESPAN_1YEAR)) }
    }
}