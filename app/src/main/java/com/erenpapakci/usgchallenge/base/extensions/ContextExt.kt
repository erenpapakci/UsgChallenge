package com.erenpapakci.usgchallenge.base.extensions

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper

tailrec fun Context.findActivity(): Activity {
    if (this is Activity) {
        @Suppress("UNCHECKED_CAST")
        return this
    } else {
        if (this is ContextWrapper) {
            return this.baseContext.findActivity()
        }
        throw IllegalStateException("The context chain does not contain Activity!")
    }
}