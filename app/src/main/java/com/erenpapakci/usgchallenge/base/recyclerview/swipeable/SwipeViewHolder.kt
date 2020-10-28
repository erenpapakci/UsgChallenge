package com.erenpapakci.usgchallenge.base.recyclerview.swipeable

import android.view.View
import com.erenpapakci.usgchallenge.base.recyclerview.ViewHolder

abstract class SwipeableViewHolder(itemView: View) : ViewHolder(itemView) {
    var deleteIconClickListener: ((viewHolder: ViewHolder, position: Int) -> Unit)? = null
    var viewBinderHelper: ViewBinderHelper? = null
}