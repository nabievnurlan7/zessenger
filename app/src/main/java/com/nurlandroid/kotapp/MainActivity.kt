package com.nurlandroid.kotapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nurlandroid.kotapp.common.CustomFragmentFactory
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val myViewModel: MyViewModel by viewModel()
    private val customFragmentFactory: CustomFragmentFactory by inject()

    private val navigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_menu -> {
                showFragment(MyFragment())
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_scan -> {
                showFragment(MyFragment())
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_settings -> {
                showFragment(MyFragment())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.fragmentFactory = customFragmentFactory

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener)

        val fragment = MyFragment()
        showFragment(fragment)
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
            .replace(R.id.customFragment, fragment, fragment.javaClass.simpleName)
            .commit()
    }
}