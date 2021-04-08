package com.mertceyhan.bitcoinmarket.features.market.di

import com.mertceyhan.bitcoinmarket.features.market.data.MarketRepository
import com.mertceyhan.bitcoinmarket.features.market.data.MarketRepositoryImp
import com.mertceyhan.bitcoinmarket.features.market.data.remote.MarketService
import com.mertceyhan.bitcoinmarket.features.market.domain.usecase.MarketInformationUseCase
import com.mertceyhan.bitcoinmarket.features.market.domain.usecase.MarketInformationUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
abstract class MarketModule {

    @Binds
    abstract fun provideMarketRepository(
        marketRepositoryImp: MarketRepositoryImp
    ): MarketRepository

    @Binds
    abstract fun provideMarketPriceUseCase(
        marketInformationUseCaseImpl: MarketInformationUseCaseImpl
    ): MarketInformationUseCase

    companion object {

        @Provides
        fun provideMarketService(
            retrofit: Retrofit
        ): MarketService = retrofit.create(MarketService::class.java)
    }
}
