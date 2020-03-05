package com.nurlandroid.kotapp

import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.nurlandroid.kotapp.common.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : BaseActivity() {
    private val myViewModel: MyViewModel by viewModel()
    private var content: FrameLayout? = null
    override var layout = R.layout.activity_main

    private val navigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_menu -> {
                val fragment = MyFragment()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_scan -> {
                val fragment = MyFragment()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_settings -> {
                val fragment = MyFragment()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        content = findViewById(R.id.content)
        val navigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        navigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener)

        val fragment = MyFragment()
        addFragment(fragment)
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
            .replace(R.id.content, fragment, fragment.javaClass.simpleName)
            .commit()
    }
}