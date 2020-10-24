package com.erenpapakci.usgchallenge.ui.detail.view

import android.graphics.Color
import android.os.Bundle
import androidx.lifecycle.Observer
import com.erenpapakci.usgchallenge.R
import com.erenpapakci.usgchallenge.base.BaseViewModelFragment
import com.erenpapakci.usgchallenge.base.extensions.currencyFormatter
import com.erenpapakci.usgchallenge.base.extensions.loadImage
import com.erenpapakci.usgchallenge.data.DataHolder
import com.erenpapakci.usgchallenge.data.Status
import com.erenpapakci.usgchallenge.data.remote.model.Coins
import com.erenpapakci.usgchallenge.ui.dashboard.viewmodel.CoinsViewModel
import com.erenpapakci.usgchallenge.ui.detail.viewmodel.CoinsDetailViewModel
import com.yabu.livechart.model.DataPoint
import com.yabu.livechart.model.Dataset
import com.yabu.livechart.view.LiveChartStyle
import kotlinx.android.synthetic.main.fragment_detail_coin.*

class CoinsDetailFragment: BaseViewModelFragment<CoinsDetailViewModel>() {

    private var coinId: Int? = null

    override fun getModelClass() = CoinsDetailViewModel::class.java
    override fun getLayoutRes(): Int = R.layout.fragment_detail_coin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        coinId = arguments?.getInt(BUNDLE_COINS_ID)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getCoinWithId(coinId)
        observeCoinWithId()
    }

    private fun observeCoinWithId(){
        viewModel.getCoinWithId.observe(this, Observer {
            when(it.status){
                Status.LOADING -> showBlockingPane()
                Status.SUCCESS -> {
                    hideBlockingPane()
                    showUI(it.data?.data?.coin)
                }
            }
        })
    }

    private fun showUI(coin: Coins?) {
        coin?.name?.let { name ->
            textViewCoinName.text = name
            textViewStatisticTitle.text =
                """$name ${resources.getString(R.string.statistics_title)}"""
        }

        coin?.iconUrl.let {
            imageViewIcon.loadImage(it)
        }

        coin?.history.let { history ->
            val dataPoint = mutableListOf<DataPoint>()
            var xValues  = 0F
            history.let { historyList ->
                historyList?.forEach { history ->
                    dataPoint.add(DataPoint(xValues,history.toFloat()))
                    xValues += 1
                }
            }
            val historyData = dataPoint?.let { Dataset(it) }

            val style = LiveChartStyle().apply {
                mainColor = R.color.colorRed
                mainFillColor = Color.MAGENTA
                baselineColor = Color.BLUE
                pathStrokeWidth = 12f
                baselineStrokeWidth = 6f
            }

            liveChart.setDataset(historyData)
                .drawSmoothPath()
                .setLiveChartStyle(style)
                .drawDataset()
        }

        coin?.price.let { price ->
            textViewStatisticPriceToUsd.text = """${price?.currencyFormatter(price)} $"""
            textViewStatisticPriceToBtc.text = """${price?.currencyFormatter(price)} $"""
        }

        coin?.rank.let { rank ->
            textViewStatisticRank.text = rank.toString()
        }

        coin?.volume.let { volume ->
            textViewStatistic24hVolume.text = """${volume?.currencyFormatter(volume)} $"""
        }

        coin?.marketCap.let { marketCap ->
            textViewStatisticMarketCap.text = """${marketCap?.currencyFormatter(marketCap)} $"""
        }
    }

    companion object {
        const val BUNDLE_COINS_ID = "coins_id"
        fun newInstance(id: Int?) =
            CoinsDetailFragment().apply {
                arguments = Bundle().apply {
                    if(id != null){
                        putInt(BUNDLE_COINS_ID, id)
                    }
                }
            }
    }
}