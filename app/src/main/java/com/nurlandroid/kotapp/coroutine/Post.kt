package com.nurlandroid.kotapp.coroutine

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Post(
    val id: Int,
    val title: String,
    var page: Int
) : Parcelable