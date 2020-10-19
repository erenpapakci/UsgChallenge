package com.erenpapakci.usgchallenge.ui.dashboard.view

import com.erenpapakci.usgchallenge.base.recyclerview.*
import com.erenpapakci.usgchallenge.ui.dashboard.view.CoinsDashboardConstants.TYPES.SHOW
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntKey
import dagger.multibindings.IntoMap

@Module
abstract class CoinsDashboardPresentationModule {

    @Binds
    @IntoMap
    @IntKey(SHOW)
    internal abstract fun bindCoinsDashboardViewHolderFactory(viewHolderFactory: CoinsDashboardViewHolder.CoinsDashboardViewHolderFactory): ViewHolderFactory

    @Binds
    @IntoMap
    @IntKey(SHOW)
    internal abstract fun bindCoinsDashboardViewHolderBinder(viewHolderBinder: CoinsDashboardViewHolder.CoinsDashboardViewHolderBinder): ViewHolderBinder

    @Module
    companion object {

        @JvmStatic
        @Provides
        fun provideDisplayItemComperator(): DisplayItemComperator = DefaultDisplayItemComperator()

        @JvmStatic
        @Provides
        fun provideRecyclerAdapter(
            itemComparator: DisplayItemComperator,
            factoryMap: Map<Int, @JvmSuppressWildcards ViewHolderFactory>,
            binderMap: Map<Int, @JvmSuppressWildcards ViewHolderBinder>
        ): RecyclerViewAdapter {
            return RecyclerViewAdapter(
                itemComperator = itemComparator,
                viewHolderFactoryMap = factoryMap,
                viewBinderFactoryMap = binderMap
            )
        }
    }
}