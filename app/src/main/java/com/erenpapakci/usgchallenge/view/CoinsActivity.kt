package com.erenpapakci.usgchallenge.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.erenpapakci.usgchallenge.R
import com.erenpapakci.usgchallenge.base.BaseActivity
import com.erenpapakci.usgchallenge.base.extensions.createAlertDialog
import com.erenpapakci.usgchallenge.base.extensions.setup
import com.erenpapakci.usgchallenge.base.recyclerview.RecyclerViewAdapter
import com.erenpapakci.usgchallenge.data.DataHolder
import com.erenpapakci.usgchallenge.data.Status
import com.erenpapakci.usgchallenge.data.model.Coins
import com.erenpapakci.usgchallenge.viewmodel.CoinsViewModel
import kotlinx.android.synthetic.main.activity_coins.*

class CoinsActivity : BaseActivity() {

    private var viewModel = CoinsViewModel()
    private var coinsList = mutableListOf<Coins>()
    private var coinsAdapter: RecyclerViewAdapter? = null

    override fun getLayoutRes(): Int = R.layout.activity_coins

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coins)
        viewModel = ViewModelProvider(this).get(CoinsViewModel::class.java)
        observeCoins()
    }

    private fun observeCoins() {
        viewModel.coinsLiveData.observe(this, Observer {
            when(it.status){
                Status.SUCCESS -> it.data.let { coinsData ->
                    coinsList = coinsData?.data?.coins as MutableList<Coins>
                    setAdapter()
                    setOnclickAdapter()
                }
                Status.ERROR -> errorAlert(it.message)
            }
        })
    }

    private fun errorAlert(error: String?) {
        createAlertDialog(title = getString(R.string.dialog_title), message = error).show()
    }

    private fun setAdapter(){
        coinsAdapter = RecyclerViewAdapter(coinsList)
        rvCoin.apply {
            coinsAdapter?.let {
                setup(context = applicationContext,
                    adapter = it
                )
            }
        }
    }

    private fun setOnclickAdapter(){
        coinsAdapter?.setOnItemClickListener(object : RecyclerViewAdapter.ListItemClickListener {
            override fun onListItemClick(position: Int) {
                showDetail(position)
            }
        })
    }

    private fun showDetail(position: Int) {
        val intent = Intent(this, CoinsDetailActivity::class.java)
        intent.putExtra("CoinDetail", coinsList[position])
        startActivity(intent)
    }
}