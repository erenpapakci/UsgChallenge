package com.erenpapakci.usgchallenge.base.recyclerview

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

open class RecyclerViewAdapter constructor(
    val items: MutableList<DisplayItem> = ArrayList(),
    private val itemComperator: DisplayItemComparator,
    private val viewHolderFactoryMap: Map<Int, ViewHolderFactory>,
    private val viewBinderFactoryMap: Map<Int, ViewHolderBinder>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), DiffAdapter {


    var itemClickListener: ((view: View, item: DisplayItem) -> Unit)? = null
    var itemLongClickListener: ((view: View, item: DisplayItem) -> Boolean)? = null
    var deleteIconClickListener: ((view: View, item: DisplayItem, position: Int) -> Unit)? = null
    var itemFavoriteClickListener: ((view: View, item: DisplayItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        viewHolderFactoryMap[viewType]?.createViewHolder(parent)!!

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        viewBinderFactoryMap[item.type()]?.bind(holder, item)
        (holder as ViewHolder).itemClickListener = itemClickListener
        holder.itemLongClickListener = itemLongClickListener
        holder.itemFavoriteClickListener = itemFavoriteClickListener
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int) = items[position].type()

    override fun update(newItems: List<DisplayItem>) {
        if (items.isEmpty()) {
            updateAllItems(newItems)
        } else {
            updateOnlyChangedItems(newItems)
        }
    }

    override fun updateAllItems(newItems: List<DisplayItem>) {
        updateItems(newItems)
        notifyDataSetChanged()
    }

    @SuppressLint("CheckResult")
    override fun updateOnlyChangedItems(newItems: List<DisplayItem>) {
        Single.fromCallable { calculateDiffResult(newItems) }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess { updateItems(newItems) }
            .subscribe(this::updateWithOnlyDiffResult)
    }

    override fun updateItems(newItems: List<DisplayItem>) {
        items.clear()
        items.addAll(newItems)
    }

    override fun calculateDiff(newItems: List<DisplayItem>): DiffUtil.DiffResult =
        DiffUtil.calculateDiff(
            DiffUtilImpl(
                items,
                newItems,
                itemComperator
            )
        )

    override fun updateWithOnlyDiffResult(result: DiffUtil.DiffResult) {
        result.dispatchUpdatesTo(this)
    }

    override fun addItems(newItems: List<DisplayItem>) {
        val startRange = items.size
        items.addAll(newItems)
        notifyItemRangeChanged(startRange, newItems.size)
    }

    private fun calculateDiffResult(newItems: List<DisplayItem>): DiffUtil.DiffResult =
        calculateDiff(newItems)
}
