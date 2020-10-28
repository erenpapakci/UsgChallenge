package com.erenpapakci.usgchallenge.ui.favorites.view

import com.erenpapakci.usgchallenge.base.recyclerview.*
import com.erenpapakci.usgchallenge.base.recyclerview.swipeable.SwipeableAdapter
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntKey
import dagger.multibindings.IntoMap


@Module
abstract class FavoritesPresentationModule {

    @Binds
    @IntoMap
    @IntKey(FavoritesConstants.TYPES.SHOW)
    internal abstract fun bindFavoritesViewHolderFactory(viewHolderFactory: FavoritesViewHolder.FavoritesViewHolderFactory): ViewHolderFactory

    @Binds
    @IntoMap
    @IntKey(FavoritesConstants.TYPES.SHOW)
    internal abstract fun bindFavoritesViewHolderBinder(viewHolderBinder: FavoritesViewHolder.FavoritesViewHolderBinder): ViewHolderBinder

    @Module
    companion object {

        @JvmStatic
        @Provides
        fun provideDisplayItemComperator(): DisplayItemComparator = DefaultDisplayItemComparator()

        @Provides
        @JvmStatic
        fun provideRecyclerViewSwipeableAdapter(
            factory: Map<Int, @JvmSuppressWildcards ViewHolderFactory>,
            binder: Map<Int, @JvmSuppressWildcards ViewHolderBinder>
        ): SwipeableAdapter {
            return SwipeableAdapter(
                viewHolderFactoryMap = factory,
                viewBinderFactoryMap = binder
            )
        }
    }
}