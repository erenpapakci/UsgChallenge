package com.erenpapakci.usgchallenge.base

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class BaseViewModelFragment<VM: ViewModel> :  Fragment() {

    protected lateinit var viewModel: VM

    abstract fun getModelClass(): Class<VM>

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(getModelClass())
    }
}