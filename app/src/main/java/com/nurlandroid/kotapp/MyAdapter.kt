package com.nurlandroid.kotapp

import android.view.ViewGroup
import com.nurlandroid.kotapp.common.BaseAdapter
import com.nurlandroid.kotapp.common.BaseViewHolder

/**
 * Created by nurik on 28.06.2019.
 */
class MyAdapter(
    private val listener: (Item) -> Unit
) : BaseAdapter<Item>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Item> =
        MyViewHolder(parent, listener)
}