package com.erenpapakci.usgchallenge.base.di.modules

import android.app.Application
import android.content.Context
import com.erenpapakci.usgchallenge.CoinsApp
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