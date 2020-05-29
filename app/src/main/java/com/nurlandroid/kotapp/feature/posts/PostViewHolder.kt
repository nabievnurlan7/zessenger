package com.nurlandroid.kotapp.feature.posts

import android.view.ViewGroup
import com.nurlandroid.kotapp.R
import com.nurlandroid.kotapp.common.BaseViewHolder
import com.nurlandroid.kotapp.feature.posts.Post
import kotlinx.android.synthetic.main.item_post.view.titleTextView

class PostViewHolder(
    parent: ViewGroup,
    listener: (Post) -> Unit
) : BaseViewHolder<Post>(R.layout.item_post, parent, listener) {

    private val title = itemView.titleTextView

    override fun onBind(item: Post) {
        super.onBind(item)
        title.text = item.title
    }
}