package com.erenpapakci.usgchallenge.base

import android.content.res.Resources
import android.os.Bundle
import android.view.Menu
import androidx.annotation.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.erenpapakci.usgchallenge.R
import com.erenpapakci.usgchallenge.base.navigation.UiNavigation
import com.erenpapakci.usgchallenge.base.recyclerview.RecyclerViewAdapter

abstract class BaseActivity: AppCompatActivity() {

    @LayoutRes
    abstract fun getLayoutRes(): Int

    @StringRes
    open val titleRes = R.string.app_name

    @MenuRes
    open val menuRes = Constants.NO_RES

    open val uiNavigation = UiNavigation.BACK

    @IdRes
    open val toolbarRes = Constants.NO_RES

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutRes())
        if (toolbarRes != Constants.NO_RES) {
            setToolbar(findViewById(toolbarRes))
        }
        initNavigation(uiNavigation)
        setScreenTitle(getString(titleRes))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (menuRes != Constants.NO_RES) {
            menuInflater.inflate(menuRes, menu)
            return true
        }

        return super.onCreateOptionsMenu(menu)
    }

    fun setScreenTitle(@StringRes titleRes: Int) {
        var title: String? = null
        try {
            title = getString(titleRes)
        } catch (e: Resources.NotFoundException) {
            // ignored
        }
        setScreenTitle(title)
    }

    fun setScreenTitle(title: String?) {
        supportActionBar?.title = title
    }

    fun setToolbar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
    }

    fun initNavigation(uiNavigation: UiNavigation) {
        when (uiNavigation) {
            UiNavigation.BACK -> supportActionBar?.setDisplayHomeAsUpEnabled(true)
            UiNavigation.ROOT -> supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }
    }
}