package com.mertceyhan.bitcoinmarket.features.market.domain.usecase

import com.mertceyhan.bitcoinmarket.core.data.State
import com.mertceyhan.bitcoinmarket.features.market.domain.model.MarketInformation
import com.mertceyhan.bitcoinmarket.features.market.domain.model.MarketInformationTimespan
import kotlinx.coroutines.flow.Flow

interface MarketInformationUseCase {

    suspend fun getMarketInformation(timespan: MarketInformationTimespan): Flow<State<MarketInformation>>
}
