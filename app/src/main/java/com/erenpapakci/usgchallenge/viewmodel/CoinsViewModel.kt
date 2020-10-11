package com.erenpapakci.usgchallenge.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.erenpapakci.usgchallenge.data.CoinsDataSource
import com.erenpapakci.usgchallenge.data.DataHolder
import com.erenpapakci.usgchallenge.data.Status
import com.erenpapakci.usgchallenge.data.model.CoinRankingModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CoinsViewModel: ViewModel() {

    private val coinsDataSource = CoinsDataSource()
    private val _coinsLiveData = MutableLiveData<DataHolder<CoinRankingModel>>()

    val coinsLiveData : LiveData<DataHolder<CoinRankingModel>>
        get() = _coinsLiveData


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
                    }
                    Status.ERROR -> {
                        _coinsLiveData.value = resource.message?.let { DataHolder.error(it) }
                    }
                }
            }
    }

}