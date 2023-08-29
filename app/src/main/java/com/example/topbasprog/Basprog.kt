package com.example.topbasprog

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Basprog (
    val name: String,
    val description: String,
    val photo: Int
    ) : Parcelable