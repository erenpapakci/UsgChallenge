package com.erenpapakci.usgchallenge.base.view

import android.app.AlertDialog
import android.content.Context
import android.media.Image
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.erenpapakci.usgchallenge.R
import java.util.zip.Inflater

class CustomAlertDialogBuilder @JvmOverloads constructor(context: Context):
      AlertDialog.Builder(context){

    private var imageView: ImageView
    private var titleText: AppCompatTextView
    private var messageText: AppCompatTextView
    private var okButton: AppCompatButton
    private var alertDialogButtonContainer: LinearLayout
    private var okClickListener: (() -> Unit)? = null
    private var isDefaultDismiss: Boolean = true

    init {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_layout, null)
        setView(dialogView)





    }


}