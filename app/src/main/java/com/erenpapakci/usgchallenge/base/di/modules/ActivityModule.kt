package com.erenpapakci.usgchallenge.base.di.modules

import com.erenpapakci.usgchallenge.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [])
    abstract fun provideMainActivity(): MainActivity
}