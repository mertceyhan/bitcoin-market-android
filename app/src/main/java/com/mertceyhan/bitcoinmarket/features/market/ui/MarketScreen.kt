package com.mertceyhan.bitcoinmarket.features.market.ui

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.mertceyhan.R
import com.mertceyhan.bitcoinmarket.components.*
import com.mertceyhan.bitcoinmarket.core.ui.UiState
import com.mertceyhan.bitcoinmarket.features.error.ErrorScreen
import com.mertceyhan.bitcoinmarket.features.error.ErrorScreenViewState


@Composable
fun MarketScreen(marketViewModel: MarketViewModel = hiltViewModel()) {

    val uiState by marketViewModel.getUiState()

    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = uiState is UiState.Loading)

    val scrollState = rememberScrollState()

    when (uiState) {
        is UiState.Success -> {

            val viewState = (uiState as UiState.Success<MarketScreenViewState>).data

            Surface {
                SwipeRefresh(
                    state = swipeRefreshState,
                    onRefresh = {
                        marketViewModel.getMarketInformation(viewState.getTimeRange())
                    },
                    modifier = Modifier.fillMaxSize(),
                ) {
                    ScreenContent(scrollState = scrollState, viewState = viewState, marketViewModel = marketViewModel, isShowShimmer = false)
                }
            }
        }
        is UiState.Error -> {
            ErrorScreen(errorScreenViewState = ErrorScreenViewState((uiState as UiState.Error).exception)) {
                marketViewModel.getMarketInformation(TimeRange.THIRTY_DAYS)
            }
        }
        is UiState.Loading -> {
            ScreenContent(scrollState = scrollState, viewState = null, marketViewModel = marketViewModel, isShowShimmer = true)
        }
    }

    LaunchedEffect(Unit) {
        marketViewModel.getMarketInformation(TimeRange.THIRTY_DAYS)
    }
}

@Composable
fun ScreenContent(scrollState: ScrollState, viewState: MarketScreenViewState? = null, marketViewModel: MarketViewModel, isShowShimmer: Boolean) {
    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
    ) {
        PriceHeader(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 16.dp, end = 16.dp),
            currency = stringResource(id = R.string.bitcoin_btc),
            price = viewState?.marketInformation?.currentPrice ?: "",
            changeRate = viewState?.marketInformation?.changeRate ?: "",
            isChangeRatePositive = viewState?.isChangeStatusPositive() ?: false,
            isShowShimmer = isShowShimmer
        )

        TimeRangePicker(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 16.dp, end = 16.dp),
            selectedTimeRange = viewState?.getTimeRange() ?: TimeRange.THIRTY_DAYS,
            isShowShimmer = isShowShimmer
        ) { timeRange ->
            marketViewModel.getMarketInformation(timeRange)
        }

        Chart(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            lineDataSet = viewState?.getLineDataSet(LocalContext.current),
            isShowShimmer = isShowShimmer
        )

        Price(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 16.dp, end = 16.dp),
            openPrice = viewState?.marketInformation?.openPrice ?: "",
            closePrice = viewState?.marketInformation?.closePrice ?: "",
            highPrice = viewState?.marketInformation?.highPrice ?: "",
            lowPrice = viewState?.marketInformation?.lowPrice ?: "",
            averagePrice = viewState?.marketInformation?.averagePrice ?: "",
            changePrice = viewState?.marketInformation?.changePrice ?: "",
            isShowShimmer = isShowShimmer
        )

        AboutChart(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 24.dp, end = 16.dp),
            aboutChart = viewState?.marketInformation?.aboutChart ?: "",
            isShowShimmer = isShowShimmer
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MarketScreenPreview() {
    MarketScreen()
}