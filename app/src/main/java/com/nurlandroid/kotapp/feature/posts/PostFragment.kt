package com.nurlandroid.kotapp.feature.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nurlandroid.kotapp.R
import com.nurlandroid.kotapp.databinding.FragmentPostsBinding
import kotlinx.android.synthetic.main.fragment_posts.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class PostFragment : Fragment(R.layout.fragment_posts) {
    private val postViewModel: PostViewModel by viewModel()
    private lateinit var binding: FragmentPostsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentPostsBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@PostFragment.viewLifecycleOwner
        }
        return binding.root
    }

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