package com.erenpapakci.usgchallenge.base

import com.erenpapakci.usgchallenge.base.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


class CoinsApp: DaggerApplication() {
    override fun onCreate() {
        super.onCreate()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }
}