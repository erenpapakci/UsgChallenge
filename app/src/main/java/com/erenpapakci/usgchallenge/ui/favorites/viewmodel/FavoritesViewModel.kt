package com.erenpapakci.usgchallenge.ui.favorites.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.erenpapakci.usgchallenge.base.BaseViewModel
import com.erenpapakci.usgchallenge.data.DataHolder
import com.erenpapakci.usgchallenge.data.Status
import com.erenpapakci.usgchallenge.data.local.FavoritesCoinDataSource
import com.erenpapakci.usgchallenge.data.local.entity.FavoritesCoinEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FavoritesViewModel(application: Application): BaseViewModel(application) {

    val favoriteCoinLiveData : LiveData<DataHolder<List<FavoritesCoinEntity>>>
        get() = _favoriteCoinLiveData

    private val _favoriteCoinLiveData = MutableLiveData<DataHolder<List<FavoritesCoinEntity>>>()

    private val favoritesCoinDataSource = FavoritesCoinDataSource(application)

    init {
        getFavoriteCoin()
    }

    @SuppressLint("CheckResult")
    private fun getFavoriteCoin(){
        favoritesCoinDataSource.getFavoriteCoins()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {resource ->
                when(resource.status){
                    Status.LOADING -> _favoriteCoinLiveData.value = DataHolder.loading()
                    Status.SUCCESS -> _favoriteCoinLiveData.value = DataHolder.success(resource.data)
                    Status.ERROR -> _favoriteCoinLiveData.value = resource.message?.let {
                        DataHolder.error(
                            it
                        )
                    }
                }

            }
    }

}