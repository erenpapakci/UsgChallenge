package com.erenpapakci.usgchallenge.base.view

import androidx.appcompat.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import com.erenpapakci.usgchallenge.R

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