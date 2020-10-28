package com.erenpapakci.usgchallenge.base.recyclerview

interface DisplayItemComparator {
    fun areItemsSame(oldItem: DisplayItem, newItem: DisplayItem): Boolean

    fun areContentsSame(oldItem: DisplayItem, newItem: DisplayItem): Boolean
}