package com.erenpapakci.usgchallenge.ui.favorites.view

import com.erenpapakci.usgchallenge.base.di.scope.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FavoritesFragmentModule {
    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributeFavoritesFragmentInjector(): FavoritesFragment

}