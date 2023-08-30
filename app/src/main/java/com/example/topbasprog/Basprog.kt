package com.example.topbasprog

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Basprog (
    val name: String,
    val description: String,
    val photo: Int,
    val like: String,
    val creator: String,
    val years: String,
    val kelebihan: String,
    val kekurangan: String
    ) : Parcelable