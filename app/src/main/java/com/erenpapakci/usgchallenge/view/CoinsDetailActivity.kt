package com.erenpapakci.usgchallenge.view

import android.content.Intent
import android.os.Bundle
import com.erenpapakci.usgchallenge.R
import com.erenpapakci.usgchallenge.base.BaseActivity
import com.erenpapakci.usgchallenge.base.extensions.createAlertDialog
import com.erenpapakci.usgchallenge.base.extensions.loadImage
import com.erenpapakci.usgchallenge.data.model.Coins
import kotlinx.android.synthetic.main.activity_detail_coin.*

class CoinsDetailActivity: BaseActivity() {
    override fun getLayoutRes(): Int = R.layout.activity_detail_coin

    private var detailCoins : Coins? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent : Intent = intent
        detailCoins = intent.getParcelableExtra<Coins>("CoinDetail")
        showDetail(detailCoins)
    }

    private fun showDetail(detailCoins: Coins?){
        imageViewCoin.loadImage(detailCoins?.iconUrl)
        textViewCoinDesc.text = detailCoins?.description

    }
}