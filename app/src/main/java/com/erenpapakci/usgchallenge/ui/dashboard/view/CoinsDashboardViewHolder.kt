package com.erenpapakci.usgchallenge.ui.dashboard.view

import android.graphics.Color
import android.provider.ContactsContract
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
import com.yabu.livechart.model.DataPoint
import com.yabu.livechart.model.Dataset
import com.yabu.livechart.view.LiveChart
import com.yabu.livechart.view.LiveChartStyle
import javax.inject.Inject


class CoinsDashboardViewHolder private constructor(itemView: View) :
    ViewHolder<CoinsDashboardEntity>(itemView) {

    private val textViewSymbol: TextView = itemView.findViewById(R.id.textViewCoinSymbol)
    private val textViewPrice: TextView = itemView.findViewById(R.id.textViewCoinPrice)
    private val imageViewCoin: ImageView = itemView.findViewById(R.id.imageViewCoin)
    private val imageViewFavorite: ImageView = itemView.findViewById(R.id.imageViewAddFavorite)
    private val liveChart: LiveChart = itemView.findViewById(R.id.liveChart)

    override fun bind(item: CoinsDashboardEntity) {
        textViewSymbol.text = item.symbol
        item.price.let { price ->
            textViewPrice.text = """${item.sign.toString()} ${price?.currencyFormatter(price)}"""
        }

        item.imageLink?.let {
            imageViewCoin.loadImage(it)
        }
        val dataPoint = mutableListOf<DataPoint>()
        var xValues  = 0F
        item.history.let { historyList ->
            historyList?.forEach { history ->
                dataPoint?.add(DataPoint(xValues,history.toFloat()))
                xValues += 1
            }
        }
        val historyData = dataPoint?.let { Dataset(it) }

        val style = LiveChartStyle().apply {
            textColor = Color.BLUE
            textHeight = 30f
            mainColor = R.color.colorRed
            mainFillColor = Color.MAGENTA
            baselineColor = Color.BLUE
            pathStrokeWidth = 12f
            baselineStrokeWidth = 6f
        }

        if (historyData != null) {
            liveChart.setDataset(historyData)
                .setLiveChartStyle(style)
                .drawDataset()
        }


        imageViewFavorite.setOnClickListener {
            imageViewFavorite.setImageResource(R.drawable.ic_favorite_red_24px)
            itemFavoriteClickListener?.invoke(item)
        }

        textViewSymbol.setOnClickListener {
            itemClickListener?.invoke(item)

        }
        textViewPrice.setOnClickListener {
            itemClickListener?.invoke(item)

        }

        imageViewCoin.setOnClickListener {
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