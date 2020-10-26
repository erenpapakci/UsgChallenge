package com.erenpapakci.usgchallenge.data.local


import com.erenpapakci.usgchallenge.data.DataHolder
import com.erenpapakci.usgchallenge.data.local.entity.FavoritesCoinEntity
import com.erenpapakci.usgchallenge.data.remote.model.Coins
import io.reactivex.BackpressureStrategy
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoritesCoinDataSource @Inject constructor(private val favoritesCoinDao: FavoritesCoinDao) {

    fun getFavoriteCoins(): Flowable<DataHolder<List<FavoritesCoinEntity>>> {
        return Flowable.create(
            { emitter ->
                emitter.onNext(DataHolder.loading())

                favoritesCoinDao?.getFavoritesCoins()
                    ?.subscribeOn(Schedulers.io())
                    ?.subscribe { favorites ->
                        emitter.onNext(DataHolder.success(favorites))
                        emitter.onComplete()
                    }
            }, BackpressureStrategy.BUFFER
        )
    }

    fun addToFavorite(coins: Coins): Completable {
        return Completable.create {
            val favoriteCoinEntity = FavoritesCoinEntity(
                coins.id,
                coins.uuid,
                coins.slug,
                coins.symbol,
                coins.name,
                coins.description,
                coins.color,
                coins.iconType,
                coins.iconUrl,
                coins.websiteUrl,
                coins.price
            )
            favoritesCoinDao?.insertFavorite(favoriteCoinEntity)
        }
    }

    fun removeFromFavorite(coinId: Int): Completable {
        return Completable.create {
            favoritesCoinDao?.removeFavorite(coinId)
        }
    }
//
//    fun removeFromAllFavorite(): Completable {
//        return Completable.create {
//            favoritesCoinDao?.removeAllFavorites()
//        }
//    }

}