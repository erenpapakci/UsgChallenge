package com.erenpapakci.usgchallenge.base.extensions

import android.view.View
import android.widget.ImageView
import com.squareup.picasso.Picasso


fun ImageView.loadImage(url: String?){
    val convertToPngUrl = url?.replace(".svg",".png")
    Picasso.get()
        .load(convertToPngUrl)
        .into(this)
}

fun ImageView.hide(){
    this.visibility = View.GONE
}

fun ImageView.show(){
    this.visibility = View.VISIBLE
}