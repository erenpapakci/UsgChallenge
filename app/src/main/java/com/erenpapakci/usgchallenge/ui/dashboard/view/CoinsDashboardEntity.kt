package com.erenpapakci.usgchallenge.ui.dashboard.view

import com.erenpapakci.usgchallenge.base.entity.ViewEntity
import com.erenpapakci.usgchallenge.base.recyclerview.DisplayItem

class CoinsDashboardEntity(
    val imageLink: String?,
    val name: String?,
    val price: String?
) : ViewEntity, DisplayItem {
    override fun type() = CoinsDashboardConstants.TYPES.SHOW
}