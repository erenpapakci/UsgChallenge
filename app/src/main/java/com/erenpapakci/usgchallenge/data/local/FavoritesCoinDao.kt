package com.erenpapakci.usgchallenge.data.local

import androidx.room.*
import com.erenpapakci.usgchallenge.data.local.entity.FavoritesCoinEntity
import io.reactivex.Flowable

@Dao
abstract class FavoritesCoinDao {

    @Query("SELECT * FROM coins")
    abstract fun getFavoritesCoins(): Flowable<List<FavoritesCoinEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertFavorite(favoritesCoinEntity: FavoritesCoinEntity)

//    @Delete
//    abstract fun removeFavorite(radioId: Int)
//
//    @Query("DELETE FROM coins ")
//    abstract fun removeAllFavorites()
}