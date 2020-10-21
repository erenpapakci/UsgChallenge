package com.erenpapakci.usgchallenge.ui.favorites.view

import android.os.Bundle
import androidx.lifecycle.Observer
import com.erenpapakci.usgchallenge.R
import com.erenpapakci.usgchallenge.base.BaseViewModelFragment
import com.erenpapakci.usgchallenge.base.extensions.createAlertDialog
import com.erenpapakci.usgchallenge.base.extensions.loadImage
import com.erenpapakci.usgchallenge.data.Status
import com.erenpapakci.usgchallenge.ui.favorites.viewmodel.FavoritesViewModel
import kotlinx.android.synthetic.main.fragment_coins_favorites.*

class FavoritesFragment: BaseViewModelFragment<FavoritesViewModel>() {

    override fun getModelClass() = FavoritesViewModel::class.java
    override fun getLayoutRes() = R.layout.fragment_coins_favorites

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeCoins()
    }

    private fun observeCoins(){
        viewModel.favoriteCoinLiveData.observe(this, Observer {
            when(it.status){
                Status.LOADING -> showBlockingPane()
                Status.SUCCESS -> {
                    hideBlockingPane()
                    showCoinName(it.data?.first()?.name!!)
                }
                Status.ERROR -> errorAlert(it.message)
            }
        })
    }

    private fun showCoinName(data: String){

    }

    private fun errorAlert(error: String?) {
        activity?.createAlertDialog(title = getString(R.string.dialog_title), message = error)?.show()
    }

    companion object{
        fun newInstance() =
            FavoritesFragment()
    }
}