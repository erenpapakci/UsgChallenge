package com.erenpapakci.usgchallenge.ui.dashboard.view

import com.erenpapakci.usgchallenge.base.entity.ViewEntity
import com.erenpapakci.usgchallenge.base.recyclerview.DisplayItem
import com.erenpapakci.usgchallenge.data.remote.model.Coins

data class CoinsDashboardEntity(
    val coin : Coins?,
    val sign : String?
) : ViewEntity, DisplayItem {
    override fun type() = CoinsDashboardConstants.TYPES.SHOW
}