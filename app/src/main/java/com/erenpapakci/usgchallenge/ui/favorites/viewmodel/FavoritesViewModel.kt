package com.erenpapakci.usgchallenge.ui.favorites.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.erenpapakci.usgchallenge.base.recyclerview.DisplayItem
import com.erenpapakci.usgchallenge.data.DataHolder
import com.erenpapakci.usgchallenge.data.Status
import com.erenpapakci.usgchallenge.data.local.FavoritesCoinDataSource
import com.erenpapakci.usgchallenge.data.local.entity.FavoritesCoinEntity
import com.erenpapakci.usgchallenge.data.remote.model.Coins
import com.erenpapakci.usgchallenge.ui.favorites.view.FavoritesDisplayItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FavoritesViewModel @Inject constructor(
    val app: Application,
    private val favoritesCoinDataSource: FavoritesCoinDataSource):
    AndroidViewModel(app) {

    val favoriteCoinLiveData : LiveData<DataHolder<List<FavoritesCoinEntity>>>
        get() = _favoriteCoinLiveData
    private val _favoriteCoinLiveData = MutableLiveData<DataHolder<List<FavoritesCoinEntity>>>()

    private val _updateCoinList = MutableLiveData<DataHolder<MutableList<DisplayItem>>>()
    val updateCoinList : LiveData<DataHolder<MutableList<DisplayItem>>>
        get() = _updateCoinList

    private val adapterList = mutableListOf<DisplayItem>()

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
                    Status.SUCCESS -> {
                        _favoriteCoinLiveData.value = DataHolder.success(resource.data)
                        addRecyclerViewAdapter(resource.data)

                    }
                    Status.ERROR -> _favoriteCoinLiveData.value = resource.message?.let {
                        DataHolder.error(
                            it
                        )
                    }
                }

            }
    }

    private fun addRecyclerViewAdapter(coinsModel: List<FavoritesCoinEntity>?) {
        coinsModel?.forEach { coin ->
            adapterList.add(
                FavoritesDisplayItem(
                    coin = coin
                )
            )
        }
        _updateCoinList.value = DataHolder.success(adapterList)
    }

     fun removeFavoriteCoin(positionId : Int?){
        if (positionId != null) {
            val coinId = (adapterList[positionId] as? FavoritesDisplayItem)?.coin?.id
            adapterList.removeAt(positionId)
            coinId?.let { id ->
                favoritesCoinDataSource.removeFromFavorite(id)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
                _updateCoinList.value = DataHolder.success(adapterList)
            }
        }
    }

}