package com.erenpapakci.usgchallenge.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.erenpapakci.usgchallenge.data.CoinsDataSource
import com.erenpapakci.usgchallenge.data.Resource
import com.erenpapakci.usgchallenge.data.Status
import com.erenpapakci.usgchallenge.data.model.CoinRankingModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CoinsViewModel: ViewModel() {

    private val coinsDataSource = CoinsDataSource()
    private val _coinsLiveData = MutableLiveData<CoinRankingModel>()

    val coinsLiveData : LiveData<CoinRankingModel>
        get() = _coinsLiveData

    init {
        getCoins()
    }

    @SuppressLint("CheckResult")
    private fun getCoins(){
        coinsDataSource.fetchCoins()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{resource ->
                when(resource.status){
                    Status.SUCCESS -> {
                        _coinsLiveData.value = resource.data
                    }
                }
            }
    }

}