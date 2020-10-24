package com.erenpapakci.usgchallenge.ui.detail.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.erenpapakci.usgchallenge.data.DataHolder
import com.erenpapakci.usgchallenge.data.Status
import com.erenpapakci.usgchallenge.data.remote.CoinsRemoteDataSource
import com.erenpapakci.usgchallenge.data.remote.detailModel.CoinDetailModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CoinsDetailViewModel @Inject constructor(val remoteDataSource: CoinsRemoteDataSource):
    ViewModel() {

    private val _getCoinWithId = MutableLiveData<DataHolder<CoinDetailModel>>()
    val getCoinWithId : LiveData<DataHolder<CoinDetailModel>>
        get() = _getCoinWithId

    @SuppressLint("CheckResult")
    fun getCoinWithId(id: Int?){
        remoteDataSource.getCoinWithId(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { resource ->
                when(resource.status){
                    Status.LOADING -> _getCoinWithId.value = DataHolder.loading()
                    Status.SUCCESS -> _getCoinWithId.value = DataHolder.success(resource.data)
                    Status.ERROR -> _getCoinWithId.value = resource.message?.let {
                        DataHolder.error(
                            it
                        )
                    }
                }

            }
    }
}