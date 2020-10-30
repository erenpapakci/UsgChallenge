package com.erenpapakci.usgchallenge.data.local

import androidx.room.*
import com.erenpapakci.usgchallenge.data.local.entity.CoinEntity
import com.erenpapakci.usgchallenge.data.local.entity.FavoritesCoinEntity
import io.reactivex.Flowable

@Dao
abstract class CoinDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertFavorite(favoritesCoinEntity: FavoritesCoinEntity)

    @Query("SELECT * FROM FavoriteCoins")
    abstract fun getFavoritesCoins(): Flowable<List<FavoritesCoinEntity>>

    @Query("DELETE FROM FavoriteCoins WHERE id = :coinId")
    abstract fun removeFavorite(coinId: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAllCoins(coinEntityList: List<CoinEntity>)

    @Query("SELECT * FROM Coins")
    abstract fun getAllCoins() : Flowable<List<CoinEntity>>
}