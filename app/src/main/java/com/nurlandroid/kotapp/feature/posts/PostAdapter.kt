package com.nurlandroid.kotapp.feature.posts

import android.view.ViewGroup
import com.nurlandroid.kotapp.common.BaseAdapter
import com.nurlandroid.kotapp.common.BaseViewHolder

class PostAdapter(
    private val listener: (Post) -> Unit
) : BaseAdapter<Post>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Post> =
        PostViewHolder(parent, listener)
}