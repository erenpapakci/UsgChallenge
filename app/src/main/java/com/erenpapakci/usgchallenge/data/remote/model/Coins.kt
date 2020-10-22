package com.erenpapakci.usgchallenge.data.remote.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Coins(
    val id: Int?,
    val uuid: String?,
    val slug:String?,
    val symbol: String?,
    val name: String?,
    val description: String?,
    val color: String?,
    val iconType: String?,
    val iconUrl: String?,
    val websiteUrl: String?,
    val price: Double?,
    val history: List<String>?
):Parcelable