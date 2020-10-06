package com.erenpapakci.usgchallenge.data

import com.erenpapakci.usgchallenge.data.model.CoinRankingModel
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET

interface CoinApiService {
    @GET("coins")
    fun getCoins(): Single<CoinRankingModel>
}