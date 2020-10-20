package com.erenpapakci.usgchallenge.base.di.modules

import com.erenpapakci.usgchallenge.base.di.scope.ActivityScope
import com.erenpapakci.usgchallenge.ui.CoinsDashboardActivity
import com.erenpapakci.usgchallenge.ui.dashboard.view.CoinsFragmentModule
import com.erenpapakci.usgchallenge.ui.detail.view.CoinsDetailFragmentModule
import com.erenpapakci.usgchallenge.ui.favorites.view.FavoritesFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [CoinsFragmentModule::class,
                                           CoinsDetailFragmentModule::class,
                                           FavoritesFragmentModule::class])
    @ActivityScope
    abstract fun contributeCoinsDashboardActivityInjector(): CoinsDashboardActivity
}