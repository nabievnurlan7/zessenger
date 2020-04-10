package com.nurlandroid.kotapp.feature

import android.view.ViewGroup
import com.nurlandroid.kotapp.common.BaseAdapter
import com.nurlandroid.kotapp.common.BaseViewHolder

class MyAdapter(
    private val listener: (MyItem) -> Unit
) : BaseAdapter<MyItem>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<MyItem> =
        MyViewHolder(parent, listener)
}