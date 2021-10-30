package com.mertceyhan.bitcoinmarket.features.market.domain.usecase

import com.mertceyhan.bitcoinmarket.features.market.data.MarketRepository
import com.mertceyhan.bitcoinmarket.features.market.domain.mapper.MarketInformationMapper
import com.mertceyhan.bitcoinmarket.features.market.domain.model.MarketInformation
import com.mertceyhan.bitcoinmarket.features.market.domain.model.MarketInformationTimespan
import javax.inject.Inject

class MarketInformationUseCaseImpl @Inject constructor(
    private val marketRepository: MarketRepository,
    private val marketModelMapper: MarketInformationMapper
) : MarketInformationUseCase {

    override suspend fun getMarketInformation(timespan: MarketInformationTimespan): MarketInformation =
        marketModelMapper.mapOnMarketPriceChartResponse(
            marketPriceChartResponse = marketRepository.fetchMarketPriceChart(timespan.value),
            timespan = timespan
        )
}
