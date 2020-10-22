package com.erenpapakci.usgchallenge.base.extensions

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

fun EditText.listenChanges(
    afterTextChangedListener: ((s: Editable?) -> Unit)? = null,
    beforeTextChangedListener: ((s: CharSequence?, start: Int, count: Int, after: Int) -> Unit)? = null,
    onTextChangedListener: ((s: CharSequence?, start: Int, before: Int, after: Int) -> Unit)? = null
) {
    addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            beforeTextChangedListener?.invoke(s,start,count,after)
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, after: Int) {
            onTextChangedListener?.invoke(s,start,before,after)
        }

        override fun afterTextChanged(s: Editable?) {
            afterTextChangedListener?.invoke(s)
        }
    })
}