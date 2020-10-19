package com.erenpapakci.usgchallenge.ui.favorites.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.erenpapakci.usgchallenge.base.BaseViewModel
import com.erenpapakci.usgchallenge.data.DataHolder
import com.erenpapakci.usgchallenge.data.Status
import com.erenpapakci.usgchallenge.data.local.FavoritesCoinDataSource
import com.erenpapakci.usgchallenge.data.local.entity.FavoritesCoinEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FavoritesViewModel @Inject constructor(
    val app: Application,
    val favoritesCoinDataSource: FavoritesCoinDataSource):
    AndroidViewModel(app) {

    val favoriteCoinLiveData : LiveData<DataHolder<List<FavoritesCoinEntity>>>
        get() = _favoriteCoinLiveData

    private val _favoriteCoinLiveData = MutableLiveData<DataHolder<List<FavoritesCoinEntity>>>()


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