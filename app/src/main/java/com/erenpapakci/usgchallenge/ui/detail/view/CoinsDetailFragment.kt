package com.erenpapakci.usgchallenge.ui.detail.view

import android.os.Bundle
import com.erenpapakci.usgchallenge.R
import com.erenpapakci.usgchallenge.base.BaseViewModelFragment
import com.erenpapakci.usgchallenge.ui.dashboard.viewmodel.CoinsViewModel
import kotlinx.android.synthetic.main.fragment_detail_coin.*

class CoinsDetailFragment: BaseViewModelFragment<CoinsViewModel>() {

    private var coinId: Int? = null

    override fun getModelClass() = CoinsViewModel::class.java
    override fun getLayoutRes(): Int = R.layout.fragment_detail_coin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        coinId = arguments?.getInt(BUNDLE_COINS_ID)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        showDetail(coinId)
    }

    private fun showDetail(id: Int?) {
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