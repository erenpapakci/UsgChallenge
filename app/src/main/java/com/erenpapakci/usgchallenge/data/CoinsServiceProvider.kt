package com.erenpapakci.usgchallenge.data

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CoinsServiceProvider {
    companion object{
        const val BASE_URL = "https://api.coinranking.com/v1/public/"
    }
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    val coinsService: CoinApiService = retrofit.create<CoinApiService>(CoinApiService::class.java)
}