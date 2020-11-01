package com.erenpapakci.usgchallenge.ui.favorites.view

import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.erenpapakci.usgchallenge.R
import com.erenpapakci.usgchallenge.base.extensions.currencyFormatter
import com.erenpapakci.usgchallenge.base.extensions.loadImage
import com.erenpapakci.usgchallenge.base.recyclerview.*
import com.erenpapakci.usgchallenge.base.recyclerview.swipeable.SwipeRevealLayout
import com.erenpapakci.usgchallenge.base.recyclerview.swipeable.Swipeable
import com.erenpapakci.usgchallenge.base.recyclerview.swipeable.SwipeableViewHolder
import javax.inject.Inject

class FavoritesViewHolder(itemView: View) : SwipeableViewHolder(itemView),
    Bindable<FavoritesDisplayItem>, Swipeable {

    override val viewBackground: LinearLayout = itemView.findViewById(R.id.viewBackground)
    override val viewForeground: LinearLayout = itemView.findViewById(R.id.llRow)
    private val registeredSwipe: SwipeRevealLayout = itemView.findViewById(R.id.registeredSwipe)

    override fun bind(item: FavoritesDisplayItem) {
         viewBinderHelper?.bind(registeredSwipe, "")
         val textViewSymbol: TextView = itemView.findViewById(R.id.textViewCoinSymbol)
         val textViewPrice: TextView = itemView.findViewById(R.id.textViewCoinPrice)
         val imageViewCoin: ImageView = itemView.findViewById(R.id.imageViewCoin)

        textViewSymbol.text = item.coin?.symbol

        item.coin?.price.let { price ->
            textViewPrice.text = """${"$"} ${price?.currencyFormatter(price)}"""
        }

        item.coin?.iconUrl?.let {
            imageViewCoin.loadImage(it)
        }

        viewBackground.setOnClickListener {
            deleteIconClickListener?.invoke(itemView, item, adapterPosition)
            registeredSwipe.close(true)
        }

        if ((adapterPosition == 0) ) {
            Handler().post { registeredSwipe.open(true) }
            Handler().postDelayed({ registeredSwipe.close(true) }, 1000L)
        }

        viewForeground.setOnClickListener {
            itemClickListener?.invoke(it, item)
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