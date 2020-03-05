package com.nurlandroid.kotapp.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by nurik on 28.06.2019.
 */
abstract class BaseViewHolder<T>(
    @LayoutRes layoutId: Int,
    parent: ViewGroup,
    private val listener: (T) -> Unit
) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(layoutId, parent, false)) {

    open fun onBind(item: T) {
        itemView.setOnClickListener {
            listener.invoke(item)
        }
    }
}