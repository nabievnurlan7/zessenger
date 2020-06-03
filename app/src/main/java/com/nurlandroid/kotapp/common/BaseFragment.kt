package com.nurlandroid.kotapp.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nurlandroid.kotapp.R

abstract class BaseFragment : Fragment(R.layout.fragment_posts) {

    abstract val layout: Int

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View =
        inflater.inflate(layout, container, false)
}