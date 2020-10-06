package com.erenpapakci.usgchallenge.data

import com.erenpapakci.usgchallenge.data.model.CoinRankingModel
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class CoinsDataSource {

    private val coinsServiceProvider = CoinsServiceProvider()

     fun fetchCoins(): Observable<Resource<CoinRankingModel>>{
        return Observable.create { emitter ->

            emitter.onNext(Resource.loading())

            coinsServiceProvider
                .coinsService
                .getCoins()
                .observeOn(Schedulers.io())
                .subscribe(
                    {coins ->
                        emitter.onNext(Resource.success(coins))
                        emitter.onComplete()
                    },
                    { error ->
                        emitter.onNext(Resource.error(error.message ?: "Error"))
                        emitter.onComplete()
                    }
                )
        }
    }

}