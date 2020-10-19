package com.erenpapakci.usgchallenge.base.di

import com.erenpapakci.usgchallenge.base.CoinsApp
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityModule::class,
        FragmentModule::class
    ]
)
interface AppComponent : AndroidInjector<CoinsApp> {
    @Component.Factory
    abstract class Factory : AndroidInjector.Factory<CoinsApp>
}