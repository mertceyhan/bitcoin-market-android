package com.mertceyhan.bitcoinmarket.core.di

import android.content.Context
import androidx.room.Room
import com.mertceyhan.bitcoinmarket.core.data.local.MarketDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataModule {

    @Provides
    @Singleton
    fun provideMarketDatabase(
        @ApplicationContext context: Context,
    ): MarketDatabase =
        Room.databaseBuilder(context, MarketDatabase::class.java, MarketDatabase.DB_NAME)
            .build()
}