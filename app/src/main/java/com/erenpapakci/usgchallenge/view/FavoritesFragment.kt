package com.erenpapakci.usgchallenge.view

import com.erenpapakci.usgchallenge.R
import com.erenpapakci.usgchallenge.base.BaseViewModelFragment
import com.erenpapakci.usgchallenge.viewmodel.CoinsViewModel

class FavoritesFragment: BaseViewModelFragment<CoinsViewModel>() {

    override fun getModelClass() = CoinsViewModel::class.java
    override fun getLayoutRes() = R.layout.fragment_coins_favorites


    companion object{
        fun newInstance() = FavoritesFragment()
    }
}