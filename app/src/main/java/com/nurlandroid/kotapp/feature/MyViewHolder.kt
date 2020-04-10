package com.nurlandroid.kotapp.feature

import android.view.ViewGroup
import com.nurlandroid.kotapp.R
import com.nurlandroid.kotapp.common.BaseViewHolder

class MyViewHolder(
    parent: ViewGroup,
    listener: (MyItem) -> Unit
) : BaseViewHolder<MyItem>(R.layout.abc_action_menu_item_layout, parent, listener) {
}