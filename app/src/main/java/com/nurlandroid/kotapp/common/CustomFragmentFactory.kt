package com.nurlandroid.kotapp.common

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.nurlandroid.kotapp.feature.posts.PostFragment

class CustomFragmentFactory : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment = when (className) {
        PostFragment::class.java.name -> PostFragment()
        else -> super.instantiate(classLoader, className)
    }
}