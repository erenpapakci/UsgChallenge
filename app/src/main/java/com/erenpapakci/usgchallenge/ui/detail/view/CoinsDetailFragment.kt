package com.erenpapakci.usgchallenge.ui.detail.view

import android.os.Bundle
import androidx.lifecycle.Observer
import com.erenpapakci.usgchallenge.R
import com.erenpapakci.usgchallenge.base.BaseViewModelFragment
import com.erenpapakci.usgchallenge.data.DataHolder
import com.erenpapakci.usgchallenge.data.Status
import com.erenpapakci.usgchallenge.data.remote.model.Coins
import com.erenpapakci.usgchallenge.ui.dashboard.viewmodel.CoinsViewModel
import com.erenpapakci.usgchallenge.ui.detail.viewmodel.CoinsDetailViewModel
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
        coin?.name
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