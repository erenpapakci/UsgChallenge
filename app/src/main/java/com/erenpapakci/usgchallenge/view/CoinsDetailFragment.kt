package com.erenpapakci.usgchallenge.view

import android.os.Bundle
import android.os.Parcelable
import com.erenpapakci.usgchallenge.R
import com.erenpapakci.usgchallenge.base.BaseViewModelFragment
import com.erenpapakci.usgchallenge.base.extensions.loadImage
import com.erenpapakci.usgchallenge.data.remote.model.Coins
import com.erenpapakci.usgchallenge.viewmodel.CoinsViewModel
import kotlinx.android.synthetic.main.fragment_detail_coin.*

class CoinsDetailFragment: BaseViewModelFragment<CoinsViewModel>() {

    private var coinsData: Coins? = null

    override fun getModelClass() = CoinsViewModel::class.java
    override fun getLayoutRes(): Int = R.layout.fragment_detail_coin

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        coinsData = arguments?.getParcelable(BUNDLE_COINS_DATA)
        showDetail(coinsData)
    }

    private fun showDetail(detailCoins: Coins?) {
        imageViewCoin.loadImage(detailCoins?.iconUrl)
        textViewCoinDesc.text = detailCoins?.description
    }

    companion object {
        const val BUNDLE_COINS_DATA = "coins_data"
        fun newInstance(data: Parcelable?) =
            CoinsDetailFragment().apply {
                arguments = Bundle().apply {
                    if(data!=null){
                        putParcelable(BUNDLE_COINS_DATA, data)
                    }
                }
            }
    }
}