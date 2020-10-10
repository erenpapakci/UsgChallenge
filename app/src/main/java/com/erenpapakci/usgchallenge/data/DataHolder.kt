package com.erenpapakci.usgchallenge.data

data class DataHolder<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): DataHolder<T> {
            return DataHolder(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String): DataHolder<T> {
            return DataHolder(Status.ERROR, null, msg)
        }

        fun <T> loading(): DataHolder<T> {
            return DataHolder(Status.LOADING, null, null)
        }
    }
}