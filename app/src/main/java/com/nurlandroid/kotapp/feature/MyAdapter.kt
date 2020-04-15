package com.nurlandroid.kotapp.feature

import android.view.ViewGroup
import com.nurlandroid.kotapp.common.BaseAdapter
import com.nurlandroid.kotapp.common.BaseViewHolder

class MyAdapter(
    private val listener: (Post) -> Unit
) : BaseAdapter<Post>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Post> =
        MyViewHolder(parent, listener)
}