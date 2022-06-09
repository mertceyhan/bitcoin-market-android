package com.mertceyhan.bitcoinmarket.features.market.domain.usecase

import com.mertceyhan.bitcoinmarket.features.market.domain.model.MarketInformation
import com.mertceyhan.bitcoinmarket.features.market.domain.model.MarketInformationTimespan

interface MarketInformationUseCase {
    suspend fun getMarketInformation(timespan: MarketInformationTimespan): MarketInformation
}
