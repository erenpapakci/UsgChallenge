package com.erenpapakci.usgchallenge.ui.favorites.view

import android.os.Bundle
import androidx.lifecycle.Observer
import com.erenpapakci.usgchallenge.R
import com.erenpapakci.usgchallenge.base.BaseViewModelFragment
import com.erenpapakci.usgchallenge.base.extensions.createAlertDialog
import com.erenpapakci.usgchallenge.base.extensions.loadImage
import com.erenpapakci.usgchallenge.base.extensions.setup
import com.erenpapakci.usgchallenge.base.recyclerview.RecyclerViewAdapter
import com.erenpapakci.usgchallenge.data.Status
import com.erenpapakci.usgchallenge.ui.favorites.viewmodel.FavoritesViewModel
import kotlinx.android.synthetic.main.fragment_coins_favorites.*
import javax.inject.Inject

class FavoritesFragment: BaseViewModelFragment<FavoritesViewModel>() {

    override fun getModelClass() = FavoritesViewModel::class.java
    override fun getLayoutRes() = R.layout.fragment_coins_favorites

    @Inject
    lateinit var favoritesAdapter : RecyclerViewAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeCoins()
        observeUpdateCoinList()
    }

    override fun initView() {
        super.initView()
        setRecyclerViewAdapter()

        favoritesAdapter.itemClickListener = {
            val id = (it as? FavoritesDisplayItem?)?.coin?.id
            id.let {
                viewModel.removeFavoriteCoin(it)
            }
        }

    }

    private fun setRecyclerViewAdapter(){
        rvFavorites.apply {
            setup(context = context, adapter = favoritesAdapter)
        }
    }

    private fun observeCoins(){
        viewModel.favoriteCoinLiveData.observe(this, Observer {
            when(it.status){
                Status.LOADING -> showBlockingPane()
                Status.SUCCESS -> hideBlockingPane()
                Status.ERROR -> errorAlert(it.message)
            }
        })
    }

    private fun observeUpdateCoinList(){
        viewModel.updateCoinList.observe(this, Observer {
            when(it.status){
                Status.SUCCESS -> it.data?.let { coinList -> favoritesAdapter.update(coinList) }
            }
        })
    }


    private fun errorAlert(error: String?) {
        activity?.createAlertDialog(title = getString(R.string.dialog_title), message = error)?.show()
    }

    companion object{
        fun newInstance() =
            FavoritesFragment()
    }
}