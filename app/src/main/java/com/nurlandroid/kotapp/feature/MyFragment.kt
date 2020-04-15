package com.nurlandroid.kotapp.feature

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nurlandroid.kotapp.R
import com.nurlandroid.kotapp.common.BaseFragment
import kotlinx.android.synthetic.main.fragment_main.catalogRecyclerView
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

        val myAdapter = MyAdapter(
            listener = { item ->
                Timber.d(item.title)
            }
        )

        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        linearLayoutManager.initialPrefetchItemCount = 20

        catalogRecyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = myAdapter
        }

        myViewModel.getData().observe(viewLifecycleOwner, Observer {
            it?.let {
                myAdapter.setItems(it)
            }
        })
    }
}