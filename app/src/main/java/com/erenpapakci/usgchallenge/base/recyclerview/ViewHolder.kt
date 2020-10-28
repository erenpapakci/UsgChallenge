package com.erenpapakci.usgchallenge.base.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.erenpapakci.usgchallenge.base.entity.ViewEntity

abstract class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var itemClickListener: ((view: View, item: DisplayItem) -> Unit)? = null
    var itemLongClickListener: ((view: View, item: DisplayItem) -> Boolean)? = null
    var itemFavoriteClickListener: ((view: View, item: DisplayItem) -> Unit)? = null
}