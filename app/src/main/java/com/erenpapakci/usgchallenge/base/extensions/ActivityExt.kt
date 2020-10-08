package com.erenpapakci.usgchallenge.base.extensions

import android.app.Activity
import androidx.appcompat.app.AlertDialog
import com.erenpapakci.usgchallenge.base.view.CustomAlertDialogBuilder

/*
 * Creates custom alert dialog builder
 */
fun Activity.createAlertDialogBuilder(
    title: CharSequence? = null,
    message: CharSequence? = null,
    defaultDismiss: Boolean = true,
    onDismissAction: (() -> Unit)? = null
): CustomAlertDialogBuilder {
    return CustomAlertDialogBuilder(this).apply {

        title?.let {
            setTitle(title)
        }

        message?.let {
            setMessage(message)
        }

        setDefaultDismiss(defaultDismiss)

        setOnDismiss {
            onDismissAction?.invoke()
        }

        setOnDismiss {
            onDismissAction?.invoke()
        }
    }
}

/*
 * Creates custom alert dialog
 */
fun Activity.createAlertDialog(
    title: CharSequence? = null,
    message: CharSequence? = null,
    defaultDismiss: Boolean = true,
    onDismissAction: (() -> Unit)? = null
): android.app.AlertDialog = createAlertDialogBuilder(
    title = title,
    message = message,
    defaultDismiss = defaultDismiss,
    onDismissAction = onDismissAction
).create()