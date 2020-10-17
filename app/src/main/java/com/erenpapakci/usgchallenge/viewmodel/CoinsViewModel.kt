package com.erenpapakci.usgchallenge.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.erenpapakci.usgchallenge.base.BaseViewModel
import com.erenpapakci.usgchallenge.data.remote.CoinsDataSource
import com.erenpapakci.usgchallenge.data.DataHolder
import com.erenpapakci.usgchallenge.data.Status
import com.erenpapakci.usgchallenge.data.local.CoinsDatabase
import com.erenpapakci.usgchallenge.data.local.FavoritesCoinDataSource
import com.erenpapakci.usgchallenge.data.remote.model.CoinRankingModel
import com.erenpapakci.usgchallenge.data.remote.model.Coins
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CoinsViewModel(application: Application): BaseViewModel(application) {

    private val coinsDataSource =
        CoinsDataSource()
    private val _coinsLiveData = MutableLiveData<DataHolder<CoinRankingModel>>()

    val coinsLiveData : LiveData<DataHolder<CoinRankingModel>>
        get() = _coinsLiveData

    private var favoriteDataSource = FavoritesCoinDataSource(application)


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

    fun addToFavorite(data: Coins) {
       favoriteDataSource.addToFavorite(data)
           .subscribeOn(Schedulers.io())
           .observeOn(AndroidSchedulers.mainThread())
           .subscribe()
    }

}