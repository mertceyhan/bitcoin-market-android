package com.mertceyhan.bitcoinmarket.features.market.domain.usecase

import com.mertceyhan.bitcoinmarket.base.data.State
import com.mertceyhan.bitcoinmarket.features.market.data.MarketRepository
import com.mertceyhan.bitcoinmarket.features.market.domain.mapper.MarketInformationMapper
import com.mertceyhan.bitcoinmarket.features.market.domain.model.MarketInformation
import com.mertceyhan.bitcoinmarket.features.market.domain.model.MarketInformationTimespan
import com.mertceyhan.bitcoinmarket.utils.extensions.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MarketInformationUseCaseImpl @Inject constructor(
    private val marketRepository: MarketRepository,
    private val marketModelMapper: MarketInformationMapper
) : MarketInformationUseCase {

    override suspend fun getMarketInformation(timespan: MarketInformationTimespan): Flow<State<MarketInformation>> =
        marketRepository
            .fetchMarketPriceChart(timespan.value)
            .map { state ->
                state.map { marketPriceChartResponse ->
                    marketModelMapper.mapOnMarketPriceChartResponse(
                        marketPriceChartResponse,
                        timespan
                    )
                }
            }
}
