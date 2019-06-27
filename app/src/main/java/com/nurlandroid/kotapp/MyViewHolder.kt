package com.nurlandroid.kotapp

import android.view.ViewGroup
import com.nurlandroid.kotapp.common.BaseViewHolder

/**
 * Created by nurik on 28.06.2019.
 */
class MyViewHolder(
    parent: ViewGroup,
    listener: (Item) -> Unit
) : BaseViewHolder<Item>(R.layout.abc_action_menu_item_layout, parent, listener) {
}