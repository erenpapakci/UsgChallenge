package com.erenpapakci.usgchallenge.ui.favorites.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.erenpapakci.usgchallenge.R
import com.erenpapakci.usgchallenge.base.extensions.currencyFormatter
import com.erenpapakci.usgchallenge.base.extensions.loadImage
import com.erenpapakci.usgchallenge.base.recyclerview.DisplayItem
import com.erenpapakci.usgchallenge.base.recyclerview.ViewHolder
import com.erenpapakci.usgchallenge.base.recyclerview.ViewHolderBinder
import com.erenpapakci.usgchallenge.base.recyclerview.ViewHolderFactory
import javax.inject.Inject

class FavoritesViewHolder private constructor(itemView: View) :
    ViewHolder<FavoritesDisplayItem>(itemView) {

    private val textViewSymbol: TextView = itemView.findViewById(R.id.textViewCoinSymbol)
    private val textViewPrice: TextView = itemView.findViewById(R.id.textViewCoinPrice)
    private val imageViewCoin: ImageView = itemView.findViewById(R.id.imageViewCoin)


    override fun bind(item: FavoritesDisplayItem) {
        textViewSymbol.text = item.coin?.symbol

        item.coin?.price.let { price ->
            textViewPrice.text = """${"$"} ${price?.currencyFormatter(price)}"""
        }

        item.coin?.iconUrl?.let {
            imageViewCoin.loadImage(it)
        }

        itemView.setOnClickListener {
            itemClickListener?.invoke(item)
        }

    }

    internal class FavoritesViewHolderFactory @Inject constructor() : ViewHolderFactory {

        override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
            FavoritesViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_favorites_coin,
                    parent,
                    false
                )
            )
    }

    internal class FavoritesViewHolderBinder @Inject constructor() : ViewHolderBinder {
        override fun bind(holder: RecyclerView.ViewHolder, item: DisplayItem) {
            (holder as FavoritesViewHolder).bind(item as FavoritesDisplayItem)
        }
    }
}