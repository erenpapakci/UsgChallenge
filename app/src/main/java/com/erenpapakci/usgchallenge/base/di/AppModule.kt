package com.erenpapakci.usgchallenge.base.di

import android.app.Application
import android.content.Context
import com.erenpapakci.usgchallenge.base.CoinsApp
import dagger.Module
import dagger.Provides

@Module
class AppModule {
    @Provides
    fun provideApp(app: CoinsApp): Application {
        return app
    }

    @Provides
    fun provideAppContext(app: CoinsApp): Context {
        return app
    }
}