package com.erenpapakci.usgchallenge.ui.dashboard.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.erenpapakci.usgchallenge.base.recyclerview.DisplayItem
import com.erenpapakci.usgchallenge.data.remote.CoinsRemoteDataSource
import com.erenpapakci.usgchallenge.data.DataHolder
import com.erenpapakci.usgchallenge.data.Status
import com.erenpapakci.usgchallenge.data.local.CoinsLocalDataSource
import com.erenpapakci.usgchallenge.data.local.entity.CoinEntity
import com.erenpapakci.usgchallenge.data.local.entity.FavoritesCoinEntity
import com.erenpapakci.usgchallenge.data.remote.model.CoinRankingModel
import com.erenpapakci.usgchallenge.data.remote.model.Coins
import com.erenpapakci.usgchallenge.ui.dashboard.view.CoinsDashboardEntity
import com.erenpapakci.usgchallenge.util.CustomSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class CoinsViewModel @Inject constructor(
    private val coinsRemoteDataSource: CoinsRemoteDataSource,
    private val coinsLocalDataSource: CoinsLocalDataSource,
    val app: Application)
    : AndroidViewModel(app){

    private val _coinsLiveData = MutableLiveData<DataHolder<List<Coins>?>>()
    val coinsLiveData : LiveData<DataHolder<List<Coins>?>>
        get() = _coinsLiveData

    private val _updateCoinList = MutableLiveData<DataHolder<MutableList<DisplayItem>>>()
    val updateCoinList : LiveData<DataHolder<MutableList<DisplayItem>>>
        get() = _updateCoinList

    private val adapterList = mutableListOf<DisplayItem>()
    private var coinsList : List<Coins>? = null

    private val customSharedPreferences = CustomSharedPreferences(getApplication())

    init {
        getCoins()
    }

    fun refreshData() {
        val savedTime = customSharedPreferences?.getTime()
        val currentTime = System.nanoTime()
        if(currentTime - savedTime!! > refreshTime) {
            getCoins()
        } else {
            getAllCoinDatabase()
        }
    }

    @SuppressLint("CheckResult")
     fun getCoins() {
        coinsRemoteDataSource.fetchCoins()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{ resource ->
                when(resource.status) {
                    Status.LOADING -> _coinsLiveData.value = DataHolder.loading()
                    Status.SUCCESS -> {
                        val coins = resource.data?.data?.coins
                        val sign = resource.data?.data?.base?.sign
                        _coinsLiveData.value = DataHolder.success(coins)
                        coinsList = coins
                        addRecyclerViewAdapter(coins, sign)
                        addAllCoinsDatabase(coins)
                    }
                    Status.ERROR -> {
                        _coinsLiveData.value = resource.message?.let { DataHolder.error(it) }
                    }
                }
            }
    }

    private fun addRecyclerViewAdapter(coinsModel: List<Coins>?, sign: String?) {
        coinsModel?.forEach { coin ->
            adapterList.add(
                CoinsDashboardEntity(
                    coin = coin,
                    sign = sign
                )
            )
        }
        _updateCoinList.value = DataHolder.success(adapterList)
    }

    fun searchResult(data: String?){
        val adapterListSearch = mutableListOf<DisplayItem>()
        if(!data.isNullOrEmpty()){
            data.let { userTextCoinName ->
                coinsList?.forEach { apiCoin ->
                    if(apiCoin.name?.toLowerCase(Locale.ROOT)?.contains(userTextCoinName?.toLowerCase(
                            Locale.ROOT
                        ).toString())!!){
                        adapterListSearch.add(
                            CoinsDashboardEntity(
                                coin = apiCoin,
                                sign = "$"
                            )
                        )
                        _updateCoinList.value = DataHolder.success(adapterListSearch)
                    } else if(adapterListSearch.isEmpty()) {
                        _updateCoinList.value = DataHolder.success(adapterList)
                    }
                }
            }
        } else {
            _updateCoinList.value = DataHolder.success(adapterList)
        }
    }

    fun addFavoriteDatabase(coin: Coins?){
        if (coin != null) {
            coinsLocalDataSource.addToFavorite(coin)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        }
    }

    private fun addAllCoinsDatabase(coins: List<Coins>?){
        if (coins!=null){
            customSharedPreferences?.saveTime(System.nanoTime())
            coinsLocalDataSource.addAllCoins(coins)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        }
    }

    @SuppressLint("CheckResult")
    private fun getAllCoinDatabase(){
        coinsLocalDataSource.getAllCoins()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { resource ->
                when(resource.status){
                    Status.LOADING -> _coinsLiveData.value = DataHolder.loading()
                    Status.SUCCESS -> {
                        val coins = convertToCoinList(resource.data)
                        _coinsLiveData.value = DataHolder.success(coins)
                        coinsList = coins
                        addRecyclerViewAdapter(coins, "$")
                    }
                    Status.ERROR ->  {
                        _coinsLiveData.value = resource.message?.let { DataHolder.error(it) }
                    }
                }

            }
    }

    private fun convertToCoinList(coinEntity: List<CoinEntity>?): List<Coins>{
        val coinList = mutableListOf<Coins>()
        coinEntity?.forEach {  coinEntity ->
            coinList.add(
                Coins(
                    coinEntity.id,
                    coinEntity.uuid,
                    coinEntity.slug,
                    coinEntity.symbol,
                    coinEntity.name,
                    coinEntity.description,
                    coinEntity.color,
                    coinEntity.iconType,
                    coinEntity.iconUrl,
                    coinEntity.websiteUrl,
                    coinEntity.price,
                    coinEntity.history,
                    coinEntity.change,
                    coinEntity.rank,
                    coinEntity.volume,
                    coinEntity.marketCap)
            )
        }
        return coinList
    }

    companion object {
        const val refreshTime = 300000000000L // 5 minute
    }
}