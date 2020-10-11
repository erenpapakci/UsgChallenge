package com.erenpapakci.usgchallenge.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.erenpapakci.usgchallenge.R
import com.erenpapakci.usgchallenge.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun getLayoutRes(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null){
           navigationFragment(CoinsFragment.newInstance())
        }

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_home -> navigationFragment(CoinsFragment.newInstance())
                R.id.nav_fav -> navigationFragment(FavoritesFragment.newInstance())
            }
            true
        }
    }

    private fun navigationFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().
        replace(R.id.framelayout_main, fragment).
        commit()
    }
}