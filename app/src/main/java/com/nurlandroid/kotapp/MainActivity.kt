package com.nurlandroid.kotapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nurlandroid.kotapp.common.CustomFragmentFactory
import com.nurlandroid.kotapp.feature.MyFragment
import com.nurlandroid.kotapp.feature.MyViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val customFragmentFactory: CustomFragmentFactory by inject()

    private val navigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.menu_item_1 -> {
                showFragment(MyFragment())
                return@OnNavigationItemSelectedListener true
            }

            R.id.menu_item_2 -> {
                showFragment(MyFragment())
                return@OnNavigationItemSelectedListener true
            }

            R.id.menu_item_3 -> {
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
        bottomNavigationView.menu.getItem(1).isChecked = true
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
            .replace(R.id.fragmentContainer, fragment, fragment.javaClass.simpleName)
            .commit()
    }
}