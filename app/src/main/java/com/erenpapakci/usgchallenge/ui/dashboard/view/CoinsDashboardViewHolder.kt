package com.erenpapakci.usgchallenge.ui.dashboard.view

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.erenpapakci.usgchallenge.R
import com.erenpapakci.usgchallenge.base.extensions.currencyFormatter
import com.erenpapakci.usgchallenge.base.extensions.findActivity
import com.erenpapakci.usgchallenge.base.extensions.loadImage
import com.erenpapakci.usgchallenge.base.recyclerview.DisplayItem
import com.erenpapakci.usgchallenge.base.recyclerview.ViewHolder
import com.erenpapakci.usgchallenge.base.recyclerview.ViewHolderBinder
import com.erenpapakci.usgchallenge.base.recyclerview.ViewHolderFactory
import com.erenpapakci.usgchallenge.ui.CoinsDashboardActivity
import javax.inject.Inject

class CoinsDashboardViewHolder private constructor(itemView: View) :
    ViewHolder<CoinsDashboardEntity>(itemView) {

    private val textViewName: TextView = itemView.findViewById(R.id.textViewCoinName)
    private val textViewPrice: TextView = itemView.findViewById(R.id.textViewCoinPrice)
    private val imageViewCoin: ImageView = itemView.findViewById(R.id.imageViewCoin)

    override fun bind(item: CoinsDashboardEntity) {
        textViewName.text = item.name
        item.price.let { price ->
            textViewPrice.text = item.sign + " " + price?.currencyFormatter(price)
        }

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
            (holder as CoinsDashboardViewHolder).bind(item as CoinsDashboardEntity)
        }
    }
}