package com.erenpapakci.usgchallenge.ui.favorites.view

import com.erenpapakci.usgchallenge.base.entity.ViewEntity
import com.erenpapakci.usgchallenge.base.recyclerview.DisplayItem
import com.erenpapakci.usgchallenge.data.local.entity.FavoritesCoinEntity

data class FavoritesDisplayItem(
    val coin : FavoritesCoinEntity?
): DisplayItem, ViewEntity {
    override fun type() = FavoritesConstants.TYPES.SHOW
    }
