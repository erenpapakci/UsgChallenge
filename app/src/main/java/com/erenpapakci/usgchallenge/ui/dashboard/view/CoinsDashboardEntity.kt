package com.erenpapakci.usgchallenge.ui.dashboard.view

import com.erenpapakci.usgchallenge.base.entity.ViewEntity
import com.erenpapakci.usgchallenge.base.recyclerview.DisplayItem

class CoinsDashboardEntity(
    val coinId: Int?,
    val imageLink: String?,
    val symbol: String?,
    val price: Double?,
    val sign: String?,
    val history: List<String>? = null,
    val change: Double?
) : ViewEntity, DisplayItem {
    override fun type() = CoinsDashboardConstants.TYPES.SHOW
}