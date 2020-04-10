package com.nurlandroid.kotapp.common

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.nurlandroid.kotapp.feature.MyFragment

class CustomFragmentFactory : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        if (className == MyFragment::class.java.name) {
            return MyFragment()
        }
        return super.instantiate(classLoader, className)
    }
}