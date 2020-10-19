package com.erenpapakci.usgchallenge.base.di.components

import com.erenpapakci.usgchallenge.CoinsApp
import com.erenpapakci.usgchallenge.base.di.modules.*
import com.erenpapakci.usgchallenge.base.di.viewmodel.ViewModelModule
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
        ViewModelModule::class,
        DatabaseModule::class,
        ApiModule::class
    ]
)
interface AppComponent : AndroidInjector<CoinsApp> {
    @Component.Factory
    abstract class Factory : AndroidInjector.Factory<CoinsApp>
}