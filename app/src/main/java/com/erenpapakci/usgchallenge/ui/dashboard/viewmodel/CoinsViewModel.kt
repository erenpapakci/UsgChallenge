package com.erenpapakci.usgchallenge.ui.dashboard.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.erenpapakci.usgchallenge.base.recyclerview.DisplayItem
import com.erenpapakci.usgchallenge.data.remote.CoinsRemoteDataSource
import com.erenpapakci.usgchallenge.data.DataHolder
import com.erenpapakci.usgchallenge.data.Status
import com.erenpapakci.usgchallenge.data.remote.model.CoinRankingModel
import com.erenpapakci.usgchallenge.data.remote.model.Coins
import com.erenpapakci.usgchallenge.ui.dashboard.view.CoinsDashboardEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CoinsViewModel @Inject constructor(val coinsDataSource: CoinsRemoteDataSource)
    : ViewModel(){

    private val _coinsLiveData = MutableLiveData<DataHolder<CoinRankingModel>>()
    val coinsLiveData : LiveData<DataHolder<CoinRankingModel>>
        get() = _coinsLiveData

    private val _updateCoinList = MutableLiveData<DataHolder<MutableList<DisplayItem>>>()
    val updateCoinList : LiveData<DataHolder<MutableList<DisplayItem>>>
        get() = _updateCoinList

    private val adapterList = mutableListOf<DisplayItem>()

    init {
        getCoins()
    }

    @SuppressLint("CheckResult")
     fun getCoins(){
        coinsDataSource.fetchCoins()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{ resource ->
                when(resource.status){
                    Status.LOADING -> _coinsLiveData.value = DataHolder.loading()
                    Status.SUCCESS -> {
                        _coinsLiveData.value = DataHolder.success(resource.data)
                        addRecyclerViewAdapter(resource.data?.data?.coins,
                            resource.data?.data?.base?.sign)
                    }
                    Status.ERROR -> {
                        _coinsLiveData.value = resource.message?.let { DataHolder.error(it) }
                    }
                }
            }
    }

    private fun addRecyclerViewAdapter(coinsModel: List<Coins>?, sign: String?) {
        coinsModel?.forEach { coin ->
            adapterList?.add(
                CoinsDashboardEntity(
                    coinId = coin.id,
                    imageLink = coin.iconUrl,
                    symbol = coin.symbol,
                    price = coin.price,
                    sign = sign
                )
            )
        }

        if(adapterList != null){
            _updateCoinList.value = DataHolder.success(adapterList)
        }
    }

}