package com.nurlandroid.kotapp.common

import androidx.recyclerview.widget.RecyclerView

/**
 * Created by nurik on 28.06.2019.
 */
abstract class BaseAdapter<T> : RecyclerView.Adapter<BaseViewHolder<T>>() {
    private var items = mutableListOf<T>()

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.onBind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun setItems(list: List<T>) {
        items.clear()
        items.addAll(list)
    }
}