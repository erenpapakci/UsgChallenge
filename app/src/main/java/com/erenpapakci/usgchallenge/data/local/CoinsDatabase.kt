package com.erenpapakci.usgchallenge.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.erenpapakci.usgchallenge.data.local.entity.CoinEntity
import com.erenpapakci.usgchallenge.data.local.entity.FavoritesCoinEntity

@Database(entities = [FavoritesCoinEntity::class, CoinEntity::class], version = 2)
@TypeConverters(ListConverter::class)
abstract class CoinsDatabase: RoomDatabase() {
    abstract fun coinsDao(): CoinDao
}