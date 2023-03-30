package com.example.androidchips

import androidx.annotation.DrawableRes

data class ChipItem(
    val id: Int,
    val text: String,
    @DrawableRes val icon: Int
)
