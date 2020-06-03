package com.nurlandroid.kotapp.feature.posts

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nurlandroid.kotapp.R
import com.nurlandroid.kotapp.common.BaseFragment
import kotlinx.android.synthetic.main.fragment_posts.catalogRecyclerView
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class PostFragment : Fragment(R.layout.fragment_posts) {
    private val postViewModel: PostViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val myAdapter = PostAdapter(
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

        postViewModel.posts.observe(viewLifecycleOwner, Observer {
            it?.let {
                myAdapter.setItems(it)
            }
        })
    }
}