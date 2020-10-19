package com.erenpapakci.usgchallenge.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.erenpapakci.usgchallenge.data.local.entity.FavoritesCoinEntity

@Database(entities = [FavoritesCoinEntity::class], version = 1)
abstract class CoinsDatabase: RoomDatabase() {
    abstract fun favoritesCoinsDao(): FavoritesCoinDao
}