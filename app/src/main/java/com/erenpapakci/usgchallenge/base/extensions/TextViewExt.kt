package com.erenpapakci.usgchallenge.base.extensions

import android.view.View
import android.widget.TextView

fun TextView.hide(){
    this.visibility = View.GONE
}

fun TextView.show(){
    this.visibility = View.VISIBLE
}