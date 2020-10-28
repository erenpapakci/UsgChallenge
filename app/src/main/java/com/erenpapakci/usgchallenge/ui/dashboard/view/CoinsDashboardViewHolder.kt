package com.erenpapakci.usgchallenge.ui.dashboard.view

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.erenpapakci.usgchallenge.R
import com.erenpapakci.usgchallenge.R.*
import com.erenpapakci.usgchallenge.base.extensions.currencyFormatter
import com.erenpapakci.usgchallenge.base.extensions.loadImage
import com.erenpapakci.usgchallenge.base.recyclerview.*
import com.yabu.livechart.model.DataPoint
import com.yabu.livechart.model.Dataset
import com.yabu.livechart.view.LiveChart
import com.yabu.livechart.view.LiveChartStyle
import kotlinx.android.synthetic.main.item_coin.view.*
import javax.inject.Inject
import kotlin.math.sign


class CoinsDashboardViewHolder (itemView: View) : ViewHolder(itemView),
    Bindable<CoinsDashboardEntity>{

    private val textViewSymbol: TextView = itemView.findViewById(id.textViewCoinSymbol)
    private val textViewPrice: TextView = itemView.findViewById(id.textViewCoinPrice)
    private val imageViewCoin: ImageView = itemView.findViewById(id.imageViewCoin)
    private val imageViewFavorite: ImageView = itemView.findViewById(id.imageViewAddFavorite)
    private val liveChart: LiveChart = itemView.findViewById(id.liveChart)
    private val textViewChange: TextView = itemView.findViewById(id.textViewChange)


    override fun bind(item: CoinsDashboardEntity) {
        textViewSymbol.text = item.coin?.symbol

        item.coin?.price.let { price ->
            textViewPrice.text = """${item.sign} ${price?.currencyFormatter(price)}"""
        }

        item.coin?.iconUrl?.let {
            imageViewCoin.loadImage(it)
        }

        item.coin?.change.let { change ->
            if (change != null){
                if(change >= 0){
                    textViewChange.text = """+ $change"""
                    textViewChange.setTextColor(itemView.resources.getColor(R.color.changePositive))
                } else {
                    textViewChange.text = change.toString()
                    textViewChange.setTextColor(itemView.resources.getColor(R.color.changeNegative))
                }
            }
        }

        val dataPoint = mutableListOf<DataPoint>()
        var xValues  = 0F
        item.coin?.history.let { historyList ->
            historyList?.forEach { history ->
                dataPoint.add(DataPoint(xValues,history.toFloat()))
                xValues += 1
            }
        }
        val historyData = dataPoint?.let { Dataset(it) }

        val style = LiveChartStyle().apply {
            mainColor = color.colorRed
            mainFillColor = Color.MAGENTA
            baselineColor = Color.BLUE
            pathStrokeWidth = 12f
            baselineStrokeWidth = 6f
        }

        liveChart.setDataset(historyData)
            .drawSmoothPath()
            .setLiveChartStyle(style)
            .drawDataset()


        imageViewFavorite.setOnClickListener {
            imageViewFavorite.setImageResource(drawable.ic_favorite_red_24px)
            itemFavoriteClickListener?.invoke(it, item)
        }

        textViewSymbol.setOnClickListener {
            itemClickListener?.invoke(it, item)

        }
        textViewPrice.setOnClickListener {
            itemClickListener?.invoke(it, item)

        }

        imageViewCoin.setOnClickListener {
            itemClickListener?.invoke(it, item)
        }


    }

    internal class CoinsDashboardViewHolderFactory @Inject constructor() : ViewHolderFactory {

        override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
            CoinsDashboardViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    layout.item_coin,
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