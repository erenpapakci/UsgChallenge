package com.erenpapakci.usgchallenge.ui.detail.view

import com.erenpapakci.usgchallenge.base.di.scope.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class CoinsDetailFragmentModule {
    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributeCoinsDetailFragmentInjector(): CoinsDetailFragment
}
