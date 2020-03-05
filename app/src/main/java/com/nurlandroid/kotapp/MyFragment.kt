package com.nurlandroid.kotapp

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.nurlandroid.kotapp.common.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

/**
 * Created by nurik on 15.07.2019.
 */
class MyFragment : BaseFragment() {
    private val myViewModel: MyViewModel by viewModel()
    override var layout = R.layout.fragment_main

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myViewModel.getData().observe(viewLifecycleOwner, Observer { item ->
            Timber.d(item.name)
        })

        val adapter = MyAdapter(
            listener = { item ->
                Timber.d(item.name)
            }
        )
    }
}