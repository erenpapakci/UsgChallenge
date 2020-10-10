package com.erenpapakci.usgchallenge.data

import com.erenpapakci.usgchallenge.data.model.CoinRankingModel
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class CoinsDataSource {

    private val coinsServiceProvider = CoinsServiceProvider()

     fun fetchCoins(): Observable<DataHolder<CoinRankingModel>>{
        return Observable.create { emitter ->

            emitter.onNext(DataHolder.loading())

            coinsServiceProvider
                .coinsService
                .getCoins()
                .observeOn(Schedulers.io())
                .subscribe(
                    {coins ->
                        emitter.onNext(DataHolder.success(coins))
                        emitter.onComplete()
                    },
                    { error ->
                        emitter.onNext(DataHolder.error(error.message ?: "Error"))
                        emitter.onComplete()
                    }
                )
        }
    }

}