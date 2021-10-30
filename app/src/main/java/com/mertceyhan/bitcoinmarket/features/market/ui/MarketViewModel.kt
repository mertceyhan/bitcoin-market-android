package com.mertceyhan.bitcoinmarket.features.market.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertceyhan.bitcoinmarket.components.TimeRange
import com.mertceyhan.bitcoinmarket.core.ui.UiState
import com.mertceyhan.bitcoinmarket.features.market.domain.model.MarketInformationTimespan
import com.mertceyhan.bitcoinmarket.features.market.domain.usecase.MarketInformationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MarketViewModel @Inject constructor(
    private val marketInformationUseCase: MarketInformationUseCase
) : ViewModel() {

    private val uiState = mutableStateOf<UiState<MarketScreenViewState>>(UiState.Loading)

    fun getUiState(): State<UiState<MarketScreenViewState>> = uiState

    fun getMarketInformation(timeRange: TimeRange) {
        viewModelScope.launch {
            uiState.value = UiState.Loading

            try {
                uiState.value = UiState.Success(
                    MarketScreenViewState(
                        marketInformation = marketInformationUseCase.getMarketInformation(
                            timespan = getTimespanByTimeRange(timeRange)
                        )
                    )
                )
            } catch (e: Exception) {
                uiState.value = UiState.Error(e)
            }
        }
    }

    private fun getTimespanByTimeRange(timeRange: TimeRange): MarketInformationTimespan =
        when (timeRange) {
            TimeRange.ONE_DAY -> MarketInformationTimespan.TIMESPAN_1DAYS
            TimeRange.SEVEN_DAYS -> MarketInformationTimespan.TIMESPAN_7DAYS
            TimeRange.THIRTY_DAYS -> MarketInformationTimespan.TIMESPAN_30DAYS
            TimeRange.SIXTY_DAYS -> MarketInformationTimespan.TIMESPAN_60DAYS
            TimeRange.NINETY_DAYS -> MarketInformationTimespan.TIMESPAN_90DAYS
            TimeRange.ONE_YEAR -> MarketInformationTimespan.TIMESPAN_1YEAR
        }
}
