package com.erenpapakci.usgchallenge.base.di.modules

import com.erenpapakci.usgchallenge.ui.dashboard.view.CoinsFragment
import com.erenpapakci.usgchallenge.ui.dashboard.di.CoinsFragmentModule
import com.erenpapakci.usgchallenge.ui.dashboard.view.CoinsDashboardPresentationModule
import com.erenpapakci.usgchallenge.ui.detail.di.CoinsDetailFragmentModule
import com.erenpapakci.usgchallenge.ui.detail.view.CoinsDetailFragment
import com.erenpapakci.usgchallenge.ui.favorites.di.FavoritesFragmentModule
import com.erenpapakci.usgchallenge.ui.favorites.view.FavoritesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector(modules = [CoinsDashboardPresentationModule::class])
    abstract fun provideCoinsFragment(): CoinsFragment

    @ContributesAndroidInjector(modules = [CoinsDetailFragmentModule::class])
    abstract fun provideCoinsDetailFragment(): CoinsDetailFragment

    @ContributesAndroidInjector(modules = [FavoritesFragmentModule::class])
    abstract fun provideFavoritesFragment(): FavoritesFragment
}