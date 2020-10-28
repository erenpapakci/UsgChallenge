package com.erenpapakci.usgchallenge.base.recyclerview.swipeable

import androidx.recyclerview.widget.RecyclerView
import com.erenpapakci.usgchallenge.base.recyclerview.*

open class SwipeableAdapter(
    items: MutableList<DisplayItem> = ArrayList(),
    itemComparator: DisplayItemComparator = DefaultDisplayItemComparator(),
    viewHolderFactoryMap: Map<Int, ViewHolderFactory>,
    private val viewBinderFactoryMap: Map<Int, ViewHolderBinder>
) : RecyclerViewAdapter(
    items,
    itemComparator,
    viewHolderFactoryMap,
    viewBinderFactoryMap
) {

    private val viewBinderHelper = ViewBinderHelper()

    init {
        viewBinderHelper.setOpenOnlyOne(true)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        if (holder is SwipeableViewHolder) {
            holder.viewBinderHelper = viewBinderHelper
            holder.deleteIconClickListener = deleteIconClickListener
        }
        if (holder is ViewHolder) {
            holder.itemClickListener = itemClickListener
            holder.itemLongClickListener = itemLongClickListener
        }
        viewBinderFactoryMap[item.type()]?.bind(holder, item)
    }
}