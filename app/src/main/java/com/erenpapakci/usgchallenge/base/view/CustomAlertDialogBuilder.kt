package com.erenpapakci.usgchallenge.base.view

import android.app.AlertDialog
import android.content.Context
import android.graphics.drawable.Drawable
import android.media.Image
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.erenpapakci.usgchallenge.R
import java.util.zip.Inflater

class CustomAlertDialogBuilder @JvmOverloads constructor(context: Context):
      AlertDialog.Builder(context){

    private var imageView: ImageView
    private var titleText: AppCompatTextView
    private var messageText: AppCompatTextView
    private var okButton: AppCompatButton
    private var okClickListener: (() -> Unit)? = null
    private var isDefaultDismiss: Boolean = true
    private var mAlertDialogDismissListener: (() -> Unit)? = null

    init {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_layout, null)
        setView(dialogView)
        imageView = dialogView.findViewById(R.id.dialogImageView)
        titleText = dialogView.findViewById(R.id.dialogTitle)
        messageText = dialogView.findViewById(R.id.dialogMessage)
        okButton = dialogView.findViewById(R.id.dialogOkButton)
    }

    override fun setTitle(title: CharSequence?): AlertDialog.Builder {
        titleText.text = title.toString()
        return this
    }

    override fun setMessage(message: CharSequence?): AlertDialog.Builder {
        messageText.text = message.toString()
        return this
    }

    fun setDefaultDismiss(isDefaultDismiss: Boolean = true) {
        this.isDefaultDismiss = isDefaultDismiss
    }

    fun setOnDismiss(onDismiss: (() -> Unit)? = null): (() -> Unit)? {
        mAlertDialogDismissListener = onDismiss
        return mAlertDialogDismissListener
    }

    override fun create(): AlertDialog {
        val alertDialog = super.create()

        alertDialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)

        okButton.setOnClickListener {
            alertDialog.dismiss()
        }

        alertDialog.setOnDismissListener {
            mAlertDialogDismissListener?.invoke()
        }
        return alertDialog
    }

}