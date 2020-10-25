package com.erenpapakci.usgchallenge.data.remote

import com.erenpapakci.usgchallenge.data.remote.detailModel.CoinDetailModel
import com.erenpapakci.usgchallenge.data.remote.model.CoinRankingModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinApiService {
    @GET("coins")
    fun getCoins(): Single<CoinRankingModel>
    @GET("coin/{id}")
    fun getCoinWithId(@Path("id") id : Int?): Single<CoinDetailModel>
}