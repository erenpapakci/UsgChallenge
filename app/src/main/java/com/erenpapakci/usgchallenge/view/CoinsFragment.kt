package com.erenpapakci.usgchallenge.view

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.erenpapakci.usgchallenge.R
import com.erenpapakci.usgchallenge.base.BaseViewModelFragment
import com.erenpapakci.usgchallenge.base.extensions.createAlertDialog
import com.erenpapakci.usgchallenge.base.extensions.setup
import com.erenpapakci.usgchallenge.base.recyclerview.RecyclerViewAdapter
import com.erenpapakci.usgchallenge.data.Status
import com.erenpapakci.usgchallenge.data.model.Coins
import com.erenpapakci.usgchallenge.viewmodel.CoinsViewModel
import kotlinx.android.synthetic.main.fragment_coins.*

class CoinsFragment: BaseViewModelFragment<CoinsViewModel>() {

    override fun getLayoutRes(): Int = R.layout.fragment_coins
    override fun getModelClass(): Class<CoinsViewModel> = CoinsViewModel::class.java

    private var coinsList = mutableListOf<Coins>()
    private var coinsAdapter: RecyclerViewAdapter? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getCoins()
        observeCoins()
    }

    private fun observeCoins() {
        viewModel.coinsLiveData.observe(this, Observer {
            when(it.status){
                Status.SUCCESS -> it.data.let { coinsData ->
                    coinsList = it.data?.data?.coins as MutableList<Coins>
                    setAdapter()
                    setOnclickAdapter()
                }
                Status.ERROR -> errorAlert(it.message)
            }
        })
    }

    private fun setAdapter() {
        coinsAdapter = RecyclerViewAdapter(coinsList)
        rvCoin.setup(
            context = context!!,
            adapter = coinsAdapter!!
        )
    }

    private fun errorAlert(error: String?) {
        activity?.createAlertDialog(title = getString(R.string.dialog_title), message = error)?.show()
    }

    private fun setOnclickAdapter(){
        coinsAdapter?.setOnItemClickListener(object : RecyclerViewAdapter.ListItemClickListener {
            override fun onListItemClick(position: Int) {
                showDetail(position)
            }
        })
    }

    private fun showDetail(position: Int) {
        fragmentManager?.beginTransaction()?.
        replace(R.id.framelayout_main,
            CoinsDetailFragment.newInstance(coinsList[position]))?.commit()
    }

    companion object {
        fun newInstance() = CoinsFragment()
    }
}

