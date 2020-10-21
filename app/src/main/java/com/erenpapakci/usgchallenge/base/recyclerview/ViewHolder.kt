package com.erenpapakci.usgchallenge.base.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.erenpapakci.usgchallenge.base.entity.ViewEntity

abstract class ViewHolder<T : ViewEntity>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var itemClickListener: ((item: DisplayItem) -> Unit)? = null
    var itemLongClickListener: ((item: DisplayItem) -> Boolean)? = null
    var itemFavoriteClickListener: ((item: DisplayItem) -> Unit)? = null

    abstract fun bind(item: T)
}