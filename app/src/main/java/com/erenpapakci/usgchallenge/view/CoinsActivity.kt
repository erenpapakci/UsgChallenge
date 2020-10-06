package com.erenpapakci.usgchallenge.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.erenpapakci.usgchallenge.R
import com.erenpapakci.usgchallenge.base.BaseActivity
import com.erenpapakci.usgchallenge.base.extensions.setup
import com.erenpapakci.usgchallenge.base.recyclerview.RecyclerViewAdapter
import com.erenpapakci.usgchallenge.data.model.Coins
import com.erenpapakci.usgchallenge.viewmodel.CoinsViewModel
import kotlinx.android.synthetic.main.activity_coins.*

class CoinsActivity : BaseActivity() {

    private var viewModel = CoinsViewModel()
    private var coinsList = mutableListOf<Coins>()
    var coinsAdapter: RecyclerViewAdapter? = null

    override fun getLayoutRes(): Int = R.layout.activity_coins

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coins)
        viewModel = ViewModelProvider(this).get(CoinsViewModel::class.java)
        observeCoins()
        coinsAdapter = RecyclerViewAdapter(coinsList)
    }

    private fun observeCoins() {
        viewModel.coinsLiveData.observe(this, Observer {
            it.data.let { coinsData ->
                coinsList = coinsData?.coins as MutableList<Coins>
                Log.v("Test", "${it.data?.coins}")
                setAdapter()
                setOnclick()
            }
        })
    }

    private fun setAdapter(){
        rvCoin.apply {
            coinsAdapter?.let {
                setup(context = applicationContext,
                    adapter = it
                )
            }
        }
    }

    private fun setOnclick() {
        coinsAdapter?.setOnItemClickListener(object : RecyclerViewAdapter.ListItemClickListener {
           override fun onListItemClick(position: Int) {
                showToastMessage(position)
            }
        })
    }

    private fun showToastMessage(position: Int) {
        Toast.makeText(this, coinsList[position].name, Toast.LENGTH_SHORT)
        coinsAdapter?.notifyItemChanged(position)
    }
}