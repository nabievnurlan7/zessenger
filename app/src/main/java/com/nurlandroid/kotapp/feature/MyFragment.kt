package com.nurlandroid.kotapp.feature

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.nurlandroid.kotapp.R
import com.nurlandroid.kotapp.common.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MyFragment : BaseFragment() {
    private val myViewModel: MyViewModel by viewModel()
    override var layout = R.layout.fragment_main

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myViewModel.getData().observe(viewLifecycleOwner, Observer { items ->
            Timber.d("$items")
        })

        val adapter = MyAdapter(
            listener = { item ->
                Timber.d(item.name)
            }
        )
    }
}