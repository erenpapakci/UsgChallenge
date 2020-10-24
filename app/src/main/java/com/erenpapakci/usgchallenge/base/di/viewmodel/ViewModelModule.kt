package com.erenpapakci.usgchallenge.base.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.erenpapakci.usgchallenge.ui.dashboard.viewmodel.CoinsViewModel
import com.erenpapakci.usgchallenge.ui.detail.viewmodel.CoinsDetailViewModel
import com.erenpapakci.usgchallenge.ui.favorites.viewmodel.FavoritesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(CoinsViewModel::class)
    internal abstract fun bindCoinsViewModel(viewModel: CoinsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavoritesViewModel::class)
    internal abstract fun bindFavoritesViewModel(viewModel: FavoritesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CoinsDetailViewModel::class)
    internal abstract fun bindCoinsDetailViewModel(viewModel: CoinsDetailViewModel): ViewModel

}