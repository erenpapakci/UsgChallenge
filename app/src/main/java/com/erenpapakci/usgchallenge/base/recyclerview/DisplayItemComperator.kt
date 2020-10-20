package com.erenpapakci.usgchallenge.base.recyclerview

interface DisplayItemComperator {
    fun areItemsSame(oldItem: DisplayItem, newItem: DisplayItem): Boolean

    fun areContentsSame(oldItem: DisplayItem, newItem: DisplayItem): Boolean
}