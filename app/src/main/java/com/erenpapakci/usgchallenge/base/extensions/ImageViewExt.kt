package com.erenpapakci.usgchallenge.base.extensions

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.ImageView
import com.ahmadrosid.svgloader.SvgLoader
import com.bumptech.glide.Glide
import com.erenpapakci.usgchallenge.R
import javax.inject.Inject


fun ImageView.loadImage (url: String?, activity: Activity){
    SvgLoader.pluck()
        .with(activity)
        .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
        .load(url, this);
}

fun ImageView.hide(){
    this.visibility = View.GONE
}

fun ImageView.show(){
    this.visibility = View.VISIBLE
}