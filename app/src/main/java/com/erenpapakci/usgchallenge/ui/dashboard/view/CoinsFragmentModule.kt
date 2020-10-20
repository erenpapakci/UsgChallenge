package com.erenpapakci.usgchallenge.ui.dashboard.view

import com.erenpapakci.usgchallenge.base.di.scope.FragmentScope
import com.erenpapakci.usgchallenge.ui.dashboard.view.CoinsDashboardPresentationModule
import com.erenpapakci.usgchallenge.ui.dashboard.view.CoinsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class CoinsFragmentModule {
    @FragmentScope
    @ContributesAndroidInjector(modules = [CoinsDashboardPresentationModule::class])
    abstract fun contributeCoinsFragmentInjector(): CoinsFragment
}