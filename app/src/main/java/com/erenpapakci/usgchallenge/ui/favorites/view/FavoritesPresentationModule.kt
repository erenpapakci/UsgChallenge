package com.erenpapakci.usgchallenge.ui.favorites.view

import com.erenpapakci.usgchallenge.base.recyclerview.*
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