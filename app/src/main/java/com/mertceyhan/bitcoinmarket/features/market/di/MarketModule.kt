package com.mertceyhan.bitcoinmarket.features.market.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import com.mertceyhan.bitcoinmarket.features.market.data.MarketRepository
import com.mertceyhan.bitcoinmarket.features.market.data.MarketRepositoryImp
import com.mertceyhan.bitcoinmarket.features.market.data.remote.MarketRemoteDataSource
import com.mertceyhan.bitcoinmarket.features.market.data.remote.MarketService
import com.mertceyhan.bitcoinmarket.features.market.domain.mapper.MarketInformationMapper
import com.mertceyhan.bitcoinmarket.features.market.domain.usecase.MarketInformationUseCase
import com.mertceyhan.bitcoinmarket.features.market.domain.usecase.MarketInformationUseCaseImpl
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
object MarketModule {

    @Provides
    fun provideMarketService(retrofit: Retrofit): MarketService =
        retrofit.create(MarketService::class.java)

    @Provides
    fun provideMarketRepository(marketRemoteDataSource: MarketRemoteDataSource): MarketRepository =
        MarketRepositoryImp(marketRemoteDataSource)

    @Provides
    fun provideMarketPriceUseCase(
        marketRepository: MarketRepository,
        marketInformationMapper: MarketInformationMapper
    ): MarketInformationUseCase =
        MarketInformationUseCaseImpl(marketRepository, marketInformationMapper)
}
