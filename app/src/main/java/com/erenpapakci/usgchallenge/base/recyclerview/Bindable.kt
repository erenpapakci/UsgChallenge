package com.erenpapakci.usgchallenge.base.recyclerview

import com.erenpapakci.usgchallenge.base.entity.ViewEntity

interface Bindable<T: ViewEntity> {
    fun bind(item :T)
}