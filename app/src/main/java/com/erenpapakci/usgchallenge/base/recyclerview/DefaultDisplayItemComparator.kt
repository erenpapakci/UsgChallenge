package com.erenpapakci.usgchallenge.base.recyclerview

class DefaultDisplayItemComparator : DisplayItemComparator {

    override fun areItemsSame(oldItem: DisplayItem, newItem: DisplayItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsSame(oldItem: DisplayItem, newItem: DisplayItem): Boolean {
        return oldItem == newItem
    }

}