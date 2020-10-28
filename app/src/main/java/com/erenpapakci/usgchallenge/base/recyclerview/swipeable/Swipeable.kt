package com.erenpapakci.usgchallenge.base.recyclerview.swipeable

import android.view.View

interface Swipeable {
    val viewBackground: View?
        get() = null
    val viewForeground: View?
        get() = null
}