package com.erenpapakci.usgchallenge.base.di.modules

import android.content.Context
import androidx.room.Room
import com.erenpapakci.usgchallenge.data.local.CoinsDatabase
import com.erenpapakci.usgchallenge.data.local.CoinDao
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    @Provides
    fun provideDatabase(context: Context): CoinsDatabase {
        return Room.databaseBuilder(context, CoinsDatabase::class.java, "coins_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideCoinDao(database: CoinsDatabase): CoinDao {
        return database.coinsDao()
    }
}