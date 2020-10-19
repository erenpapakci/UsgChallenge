package com.erenpapakci.usgchallenge.base.di.modules

import com.erenpapakci.usgchallenge.data.remote.CoinApiService
import com.erenpapakci.usgchallenge.data.remote.CoinsRemoteDataSource
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    @Named(NAME_URL)
    fun provideBaseUrl(): String = "https://api.coinranking.com/v1/public/"

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        with(OkHttpClient.Builder()) {
            addInterceptor(loggingInterceptor).build()
        }

    @Provides
    @Singleton
    fun provideRetrofit(@Named(NAME_URL) baseUrl: String, client: OkHttpClient): Retrofit =
        with(Retrofit.Builder()) {
            baseUrl(baseUrl)
            client(client)
            addConverterFactory(GsonConverterFactory.create())
            addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            build()
        }

    @Provides
    @Singleton
    fun provideCoinApiService(retrofit: Retrofit): CoinApiService =
        retrofit.create(CoinApiService::class.java)

    @Provides
    @Singleton
    fun provideCoinsRemoteDataSource(coinsApiService: CoinApiService): CoinsRemoteDataSource =
        CoinsRemoteDataSource(coinsApiService)

    companion object {
        private const val NAME_URL = "url"
    }
}