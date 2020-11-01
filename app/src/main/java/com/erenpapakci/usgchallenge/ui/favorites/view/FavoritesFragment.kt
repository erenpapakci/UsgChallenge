package com.erenpapakci.usgchallenge.ui.favorites.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.erenpapakci.usgchallenge.R
import com.erenpapakci.usgchallenge.base.BaseViewModelFragment
import com.erenpapakci.usgchallenge.base.extensions.createAlertDialog
import com.erenpapakci.usgchallenge.base.extensions.setup
import com.erenpapakci.usgchallenge.base.recyclerview.DisplayItem
import com.erenpapakci.usgchallenge.base.recyclerview.ViewHolder
import com.erenpapakci.usgchallenge.base.recyclerview.swipeable.SwipeableAdapter
import com.erenpapakci.usgchallenge.data.Status
import com.erenpapakci.usgchallenge.ui.detail.view.CoinsDetailFragment
import com.erenpapakci.usgchallenge.ui.favorites.viewmodel.FavoritesViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_coins_favorites.*
import javax.inject.Inject

class FavoritesFragment: BaseViewModelFragment<FavoritesViewModel>() {

    override fun getModelClass() = FavoritesViewModel::class.java
    override fun getLayoutRes() = R.layout.fragment_coins_favorites

    @Inject
    lateinit var favoritesAdapter : SwipeableAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeCoins()
        observeUpdateCoinList()
    }

    override fun initView() {
        super.initView()
        setRecyclerViewAdapter()

        favoritesAdapter.itemClickListener = { _:View, item: DisplayItem ->
            val coinId = (item as? FavoritesDisplayItem)?.coin?.id
            navigateToDetailFragment(coinId)
        }

        favoritesAdapter.deleteIconClickListener = { view: View, displayItem : DisplayItem,
                                                     position: Int ->
            position.let { positionId ->
                val coinName = (displayItem as? FavoritesDisplayItem)?.coin?.name
                showSnackBar(view, coinName)
                callRemoveFavoriteCoin(positionId)
            }
        }

    }

    private fun showSnackBar(view: View, coinName: String?){
        val snackMessage = coinName + " " + resources.getString(R.string.favorites_coin_remove_message)
        val snack = Snackbar.make(view,snackMessage, Snackbar.LENGTH_SHORT)
        snack.setBackgroundTint(resources.getColor(R.color.progressColor))
        snack.show()
    }

    private fun callRemoveFavoriteCoin(id: Int?){
        viewModel.removeFavoriteCoin(id)

    }

    private fun navigateToDetailFragment(id: Int?){
        fragmentManager?.beginTransaction()?.replace(
            R.id.framelayout_main,
            CoinsDetailFragment.newInstance(id)
        )?.commit()
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
                Status.SUCCESS ->
                    if(!it.data.isNullOrEmpty()) {
                       favoritesAdapter.update(it.data)
                    } else {
                        it.data?.let { it1 -> favoritesAdapter.update(it1) }
                        showNullMessage()
                    }
            }
        })
    }

    private fun showNullMessage(){
        llNullMessage.visibility = View.VISIBLE
        textViewNullMessage.text = resources.getString(R.string.favorites_null_message)
    }

    private fun errorAlert(error: String?) {
        activity?.createAlertDialog(title = getString(R.string.dialog_title), message = error)?.show()
    }

    companion object{
        fun newInstance() =
            FavoritesFragment()
    }
}