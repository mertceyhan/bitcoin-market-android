package com.mertceyhan.bitcoinmarket.data.remote

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Suppress("unused")
@Module
@InstallIn(SingletonComponent::class)
object RemoteDataModule {

    private const val BLOCKCHAIN_API_URL = "https://api.blockchain.info/"

    @Singleton
    @Provides
    fun provideRetrofit(
        blockchainApiUrl: String,
        okHttpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(blockchainApiUrl)
            .addConverterFactory(moshiConverterFactory)
            .client(okHttpClient)
            .build()

    @Provides
    fun provideBlockchainApiUrl(): String = BLOCKCHAIN_API_URL

    @Singleton
    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideMoshiConverterFactory(): MoshiConverterFactory =
        MoshiConverterFactory.create()

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BASIC)
        }
}
