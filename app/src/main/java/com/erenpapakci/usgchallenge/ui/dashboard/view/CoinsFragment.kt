package com.erenpapakci.usgchallenge.ui.dashboard.view

import android.os.Bundle
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import com.erenpapakci.usgchallenge.R
import com.erenpapakci.usgchallenge.base.BaseViewModelFragment
import com.erenpapakci.usgchallenge.base.extensions.createAlertDialog
import com.erenpapakci.usgchallenge.base.extensions.listenChanges
import com.erenpapakci.usgchallenge.base.extensions.setup
import com.erenpapakci.usgchallenge.base.recyclerview.DisplayItem
import com.erenpapakci.usgchallenge.base.recyclerview.RecyclerViewAdapter
import com.erenpapakci.usgchallenge.data.Status
import com.erenpapakci.usgchallenge.ui.dashboard.viewmodel.CoinsViewModel
import com.erenpapakci.usgchallenge.ui.detail.view.CoinsDetailFragment
import kotlinx.android.synthetic.main.fragment_coins.*
import javax.inject.Inject

open class CoinsFragment: BaseViewModelFragment<CoinsViewModel>() {

    override fun getLayoutRes(): Int = R.layout.fragment_coins
    override fun getModelClass(): Class<CoinsViewModel> = CoinsViewModel::class.java

    @Inject
    protected lateinit var coinsDashboardAdapter: RecyclerViewAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeCoins()
        observeUpdateAdapter()
    }

    override fun initView() {
        super.initView()
        recyclerViewSetAdapter()
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.getCoins()
        }

        editTextSearch.listenChanges(
            afterTextChangedListener = {
                it?.let { result ->
                    viewModel.searchResult(result.toString().toLowerCase())
                }
            })

    }

    private fun observeCoins() {
        viewModel.coinsLiveData.observe(this, Observer {
            when(it.status){
                Status.LOADING -> showBlockingPane()
                Status.SUCCESS -> {
                    it.data?.data?.let { coinsList ->
                        swipeRefreshLayout.isRefreshing = false
                        hideBlockingPane()
                    }
                }
                Status.ERROR -> errorAlert(it.message)
            }
        })
    }

    private fun observeUpdateAdapter(){
        viewModel.updateCoinList.observe(this, Observer {
            when(it.status){
                Status.LOADING -> showBlockingPane()
                Status.SUCCESS -> {
                    updateAdapter(it.data)
                    hideBlockingPane()
                }
            }
        })
    }

    private fun updateAdapter(displayItemList: List<DisplayItem>?){
       if(displayItemList != null){
           coinsDashboardAdapter.updateAllItems(displayItemList)
       }
    }

    private fun recyclerViewSetAdapter(){
        rvCoin.apply {
            setup(context = context!!, adapter = coinsDashboardAdapter)
        }
    }

    private fun navigateToDetailFragment(id: Int?){
        fragmentManager?.beginTransaction()?.replace(
            R.id.framelayout_main,
            CoinsDetailFragment.newInstance(id)
        )?.commit()
    }

    private fun errorAlert(error: String?) {
        activity?.createAlertDialog(title = getString(R.string.dialog_title), message = error)?.show()
    }

    companion object {
        fun newInstance() =
            CoinsFragment()
    }
}

