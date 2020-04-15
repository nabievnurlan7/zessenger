package com.nurlandroid.kotapp.feature

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Post(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    var page: Int
) : Parcelable