package com.erenpapakci.usgchallenge.data.remote

import com.erenpapakci.usgchallenge.data.remote.model.CoinRankingModel
import io.reactivex.Single
import retrofit2.http.GET

interface CoinApiService {
    @GET("coins")
    fun getCoins(): Single<CoinRankingModel>
}