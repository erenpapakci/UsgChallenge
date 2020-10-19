package com.erenpapakci.usgchallenge.data.remote

import com.erenpapakci.usgchallenge.data.DataHolder
import com.erenpapakci.usgchallenge.data.remote.model.CoinRankingModel
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CoinsDataSource @Inject constructor(val coinsServiceProvider: CoinsServiceProvider) {

     fun fetchCoins(): Observable<DataHolder<CoinRankingModel>>{
        return Observable.create { emitter ->

            emitter.onNext(DataHolder.loading())

            coinsServiceProvider
                .coinsService
                .getCoins()
                .observeOn(Schedulers.io())
                .subscribe(
                    { coins ->
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