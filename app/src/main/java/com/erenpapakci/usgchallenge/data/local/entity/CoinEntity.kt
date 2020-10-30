package com.erenpapakci.usgchallenge.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.erenpapakci.usgchallenge.data.local.ListConverter

@TypeConverters(ListConverter::class)
@Entity(tableName = "Coins")
data class CoinEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int?,
    @ColumnInfo(name = "uuid") val uuid: String?,
    @ColumnInfo(name = "slug") val slug:String?,
    @ColumnInfo(name = "symbol") val symbol: String?,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "color") val color: String?,
    @ColumnInfo(name = "iconType") val iconType: String?,
    @ColumnInfo(name = "iconUrl") val iconUrl: String?,
    @ColumnInfo(name = "websiteUrl") val websiteUrl: String?,
    @ColumnInfo(name = "price") val price: Double?,
    @ColumnInfo(name = "history") val history: List<String>?,
    @ColumnInfo(name = "change") val change: Double?,
    @ColumnInfo(name = "rank") val rank: Int?,
    @ColumnInfo(name = "volume") val volume: Double?,
    @ColumnInfo(name = "marketCap") val marketCap: Double?)