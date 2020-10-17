package com.erenpapakci.usgchallenge.base

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.erenpapakci.usgchallenge.R
import com.erenpapakci.usgchallenge.view.MainActivity

abstract class BaseViewModelFragment<VM: ViewModel> :  BaseFragment() {

    protected lateinit var viewModel: VM

    abstract fun getModelClass(): Class<VM>

    private var blockingOperationCount = 0
    private var blockingPane: Dialog? = null

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(getModelClass())
    }

    override fun onDestroyView() {
        blockingPane = null
        super.onDestroyView()
    }

    protected fun showBlockingPane() {
        if (blockingOperationCount == 0){
            if (blockingPane == null){
                blockingPane = createProgressDialog()
            }
            blockingPane?.show()
            blockingPane?.let {
                if(!it.isShowing)
                    it.show()
            }
        }
        blockingOperationCount += 1
    }

    protected fun hideBlockingPane() {
        blockingOperationCount -= 1
        if(blockingOperationCount == 0){
            blockingPane?.dismiss()
            blockingPane = null
        }
    }

    private fun createProgressDialog(): Dialog {
        return Dialog(requireContext()).apply {
            setCancelable(true)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setContentView(R.layout.view_progress)
        }
    }


}