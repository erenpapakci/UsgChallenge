package com.erenpapakci.usgchallenge.ui.dashboard.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.erenpapakci.usgchallenge.R
import com.erenpapakci.usgchallenge.base.extensions.loadImage
import com.erenpapakci.usgchallenge.base.recyclerview.DisplayItem
import com.erenpapakci.usgchallenge.base.recyclerview.ViewHolder
import com.erenpapakci.usgchallenge.base.recyclerview.ViewHolderBinder
import com.erenpapakci.usgchallenge.base.recyclerview.ViewHolderFactory
import javax.inject.Inject

class CoinsDashboardViewHolder private constructor(itemView: View) :
    ViewHolder<CoinsDashboardEntity>(itemView) {

    private val textViewName: TextView = itemView.findViewById(R.id.textViewCoinName)
    private val textViewPrice: TextView = itemView.findViewById(R.id.textViewCoinPrice)
    private val imageViewCoin: ImageView = itemView.findViewById(R.id.imageViewCoin)

    override fun bind(item: CoinsDashboardEntity) {
        textViewName.text = item.name
        textViewPrice.text = item.price
        Log.v("ViewHolderTest", "ViewHolderTest")
        item.imageLink?.let {
            imageViewCoin.loadImage(it)
        }
        itemView.setOnClickListener {
            itemClickListener?.invoke(item)
        }
    }

    internal class CoinsDashboardViewHolderFactory @Inject constructor() : ViewHolderFactory {

        override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
            CoinsDashboardViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_coin,
                    parent,
                    false
                )
            )
    }

    internal class CoinsDashboardViewHolderBinder @Inject constructor() : ViewHolderBinder {

        override fun bind(holder: RecyclerView.ViewHolder, item: DisplayItem) {
            Log.v("ViewHolderTest", "ViewHolderTest")

            (holder as CoinsDashboardViewHolder).bind(item as CoinsDashboardEntity)
        }
    }
}