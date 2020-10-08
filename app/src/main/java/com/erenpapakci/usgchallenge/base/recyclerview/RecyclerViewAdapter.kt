package com.erenpapakci.usgchallenge.base.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.erenpapakci.usgchallenge.R
import com.erenpapakci.usgchallenge.base.extensions.loadImage
import com.erenpapakci.usgchallenge.base.extensions.twoDigit
import com.erenpapakci.usgchallenge.data.model.Coins


class RecyclerViewAdapter (private val coinsData: MutableList<Coins>):
    RecyclerView.Adapter<RecyclerViewAdapter.CoinsViewHolder>() {

    private var listItemClickListener: ListItemClickListener? = null

    class CoinsViewHolder(view: View, onItemClickListener: ListItemClickListener): RecyclerView.ViewHolder(view) {

        private val onItemClickListener = onItemClickListener

        private val coinImage : ImageView = view.findViewById(R.id.imageViewCoin)
        private val coinName : TextView = view.findViewById(R.id.textViewCoinName)
        private val coinPrice : TextView = view.findViewById(R.id.textViewCoinPrice)

        fun bindItems(data: Coins) {
            coinImage.loadImage(data.iconUrl)
            coinName.text = data.name?.toUpperCase()
            coinPrice.text = data?.price?.twoDigit(data.price).toString()

            coinName.setOnClickListener  {
                if (onItemClickListener != null) {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        onItemClickListener.onListItemClick(position)
                    }
                }
            }

            coinImage.setOnClickListener  {
                if (onItemClickListener != null) {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        onItemClickListener.onListItemClick(position)
                    }
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinsViewHolder {
        val view = LayoutInflater.from(parent.context).
            inflate(R.layout.item_coin, parent, false)
        return CoinsViewHolder(view, listItemClickListener!!)
    }

    override fun getItemCount(): Int = coinsData.size

    override fun onBindViewHolder(holder: CoinsViewHolder, position: Int) {
        holder.bindItems(coinsData[position])
    }

    interface ListItemClickListener {
        fun onListItemClick(position: Int)
    }

    fun setOnItemClickListener(listItemClickListener : ListItemClickListener){
        this.listItemClickListener = listItemClickListener
    }

}
