package com.mertceyhan.bitcoinmarket.features.market.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.mertceyhan.bitcoinmarket.core.ui.LayoutViewState
import com.mertceyhan.bitcoinmarket.features.market.domain.model.MarketInformationFactory
import com.mertceyhan.bitcoinmarket.features.market.domain.model.MarketInformationTimespan
import com.mertceyhan.bitcoinmarket.features.market.domain.usecase.MarketInformationUseCase
import com.mertceyhan.bitcoinmarket.utils.CoroutinesTestRule
import com.mertceyhan.bitcoinmarket.utils.`should be`
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.net.SocketTimeoutException

@ExperimentalCoroutinesApi
class MarketViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    private lateinit var marketViewModel: MarketViewModel

    private val marketInformationUseCase = mockk<MarketInformationUseCase>()

    private val marketViewStateLiveDataObserver = mockk<Observer<MarketViewState>>()

    private val marketViewStateLiveDataSlot = slot<MarketViewState>()

    private val marketViewStateLiveDataValues = arrayListOf<MarketViewState>()

    private val layoutViewStateLiveDataObserver = mockk<Observer<LayoutViewState>>()

    private val layoutViewStateLiveDataSlot = slot<LayoutViewState>()

    private val layoutViewStateLiveDataValues = arrayListOf<LayoutViewState>()

    @Before
    fun setUp() {
        marketViewModel = MarketViewModel(marketInformationUseCase)
    }

    @Test
    fun `check getMarketInformation() success case`() {
        // given
        val timespan = MarketInformationTimespan.TIMESPAN_1DAYS
        val marketInformation = MarketInformationFactory.getMockMarketInformation()

        marketViewModel.getMarketViewStateLiveData().observeForever(marketViewStateLiveDataObserver)
        marketViewModel.getLayoutViewStateLiveData().observeForever(layoutViewStateLiveDataObserver)

        every { marketViewStateLiveDataObserver.onChanged(capture(marketViewStateLiveDataSlot)) } answers {
            marketViewStateLiveDataValues.add(marketViewStateLiveDataSlot.captured)
        }
        every { layoutViewStateLiveDataObserver.onChanged(capture(layoutViewStateLiveDataSlot)) } answers {
            layoutViewStateLiveDataValues.add(layoutViewStateLiveDataSlot.captured)
        }
        coEvery { marketInformationUseCase.getMarketInformation(timespan) } returns flow {
            emit(State.Loading)
            emit(State.Success(marketInformation))
        }

        // when
        marketViewModel.getMarketInformation(timespan)

        // then
        marketViewStateLiveDataValues[0].marketInformation `should be` marketInformation
        marketViewStateLiveDataValues.size `should be` 1

        layoutViewStateLiveDataValues[0].isLoading() `should be` true
        layoutViewStateLiveDataValues[1].isSuccess() `should be` true

        coVerify(exactly = 1) { marketInformationUseCase.getMarketInformation(timespan) }
    }

    @Test
    fun `check getMarketInformation() fail case`() {
        // given
        val timespan = MarketInformationTimespan.TIMESPAN_1DAYS
        val error = SocketTimeoutException()

        marketViewModel.getMarketViewStateLiveData().observeForever(marketViewStateLiveDataObserver)
        marketViewModel.getLayoutViewStateLiveData().observeForever(layoutViewStateLiveDataObserver)

        every { marketViewStateLiveDataObserver.onChanged(capture(marketViewStateLiveDataSlot)) } answers {
            marketViewStateLiveDataValues.add(marketViewStateLiveDataSlot.captured)
        }
        every { layoutViewStateLiveDataObserver.onChanged(capture(layoutViewStateLiveDataSlot)) } answers {
            layoutViewStateLiveDataValues.add(layoutViewStateLiveDataSlot.captured)
        }
        coEvery { marketInformationUseCase.getMarketInformation(timespan) } returns flow {
            emit(State.Loading)
            emit(State.Error(error))
        }

        // when
        marketViewModel.getMarketInformation(timespan)

        // then
        marketViewStateLiveDataValues.size `should be` 0

        layoutViewStateLiveDataValues[0].isLoading() `should be` true
        layoutViewStateLiveDataValues[1].isError() `should be` true

        coVerify(exactly = 1) { marketInformationUseCase.getMarketInformation(timespan) }
    }
}
