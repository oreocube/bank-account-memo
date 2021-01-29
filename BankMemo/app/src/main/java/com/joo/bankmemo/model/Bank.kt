package com.joo.bankmemo.model

import androidx.annotation.DrawableRes

data class Bank(
    @DrawableRes
    val symbol: Int?,
    val title: String
)