package com.erenpapakci.usgchallenge.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListConverter {
    @TypeConverter
    fun stringListToJson(stringList: List<String>?): String {
        if (stringList.isNullOrEmpty()) {
            return ""
        }
        val gson = Gson()
        val type = object : TypeToken<List<String>>() {}.type
        return gson.toJson(stringList, type)
    }

    @TypeConverter
    fun jsonToStringList(stringListJson: String): List<String> {
        val gson = Gson()
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(stringListJson, type)
    }
}

