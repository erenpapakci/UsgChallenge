package com.erenpapakci.usgchallenge.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CoinsServiceProvider {
    companion object{
        const val BASE_URL = "https://api.coinranking.com/v1/public/"
    }

    private val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val okHttp = OkHttpClient.Builder().addInterceptor(loggingInterceptor)

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttp.build())
        .build()

    val coinsService: CoinApiService = retrofit.create<CoinApiService>(
        CoinApiService::class.java)
}