package com.erenpapakci.usgchallenge.ui.dashboard.view

import android.os.Bundle
import androidx.lifecycle.Observer
import com.erenpapakci.usgchallenge.R
import com.erenpapakci.usgchallenge.base.BaseViewModelFragment
import com.erenpapakci.usgchallenge.base.extensions.createAlertDialog
import com.erenpapakci.usgchallenge.base.extensions.setup
import com.erenpapakci.usgchallenge.base.recyclerview.DisplayItem
import com.erenpapakci.usgchallenge.base.recyclerview.RecyclerViewAdapter
import com.erenpapakci.usgchallenge.data.Status
import com.erenpapakci.usgchallenge.data.remote.model.Coins
import com.erenpapakci.usgchallenge.ui.dashboard.viewmodel.CoinsViewModel
import com.erenpapakci.usgchallenge.ui.detail.view.CoinsDetailFragment
import kotlinx.android.synthetic.main.fragment_coins.*
import javax.inject.Inject

open class CoinsFragment: BaseViewModelFragment<CoinsViewModel>() {

    override fun getLayoutRes(): Int = R.layout.fragment_coins
    override fun getModelClass(): Class<CoinsViewModel> = CoinsViewModel::class.java

    @Inject
    protected lateinit var coinsDashboardAdapter: RecyclerViewAdapter
    var updateCoinList = mutableListOf<DisplayItem>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getCoins()
        observeCoins()
    }

    private fun observeCoins() {
        viewModel.coinsLiveData.observe(this, Observer {
            when(it.status){
                Status.LOADING -> showBlockingPane()
                Status.SUCCESS -> {
                    it.data?.data?.let { coinsList ->
                        addDisplayItem(coinsList.coins, coinsList.base?.sign)
                        swipeRefreshLayout.isRefreshing = false
                        hideBlockingPane()
                    }
                }
                Status.ERROR -> errorAlert(it.message)
            }
        })
    }

    override fun initView() {
        super.initView()

        rvCoin.apply {
            setup(context = context!!, adapter = coinsDashboardAdapter)
        }

        swipeRefreshLayout.setOnRefreshListener {
            viewModel.getCoins()
        }

        coinsDashboardAdapter.itemClickListener = {
             val coinId = (it as? CoinsDashboardEntity)?.coinId
            navigateToDetailFragment(id = coinId)
        }

        coinsDashboardAdapter.itemFavoriteClickListener = {
            val coinId = (it as? CoinsDashboardEntity)?.coinId
        }

    }

    private fun navigateToDetailFragment(id: Int?){
        fragmentManager?.beginTransaction()?.replace(
            R.id.framelayout_main,
            CoinsDetailFragment.newInstance(id)
        )?.commit()
    }

    private fun addDisplayItem(coinsList: List<Coins>?, sign: String?) {
        coinsList?.forEach { coin ->
            updateCoinList.add(
                CoinsDashboardEntity(
                    coinId = coin.id,
                    symbol = coin.symbol,
                    price = coin.price,
                    imageLink = coin.iconUrl,
                    sign = sign
                )
            )
        }
        coinsDashboardAdapter.update(updateCoinList)
    }

    private fun errorAlert(error: String?) {
        activity?.createAlertDialog(title = getString(R.string.dialog_title), message = error)?.show()
    }

    companion object {
        fun newInstance() =
            CoinsFragment()
    }
}

