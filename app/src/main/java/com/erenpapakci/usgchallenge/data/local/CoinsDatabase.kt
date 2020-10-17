package com.erenpapakci.usgchallenge.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.erenpapakci.usgchallenge.data.local.entity.FavoritesCoinEntity

@Database(entities = [FavoritesCoinEntity::class], version = 1)
abstract class CoinsDatabase: RoomDatabase() {

    abstract fun favoritesCoinsDao(): FavoritesCoinDao

    //singleton

    companion object {
        @Volatile private var instance : CoinsDatabase? = null

        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: makeDatabase(context).also {
                instance = it
            }
        }

        private fun makeDatabase(context: Context) =
            Room.databaseBuilder(context?.applicationContext,
            CoinsDatabase::class.java, "coins_db").build()
    }

}