package com.example.composelearn

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

data class IntroPage(
    val title: String,
    val description: String,
    @DrawableRes val image: Int,
    val color: Color
)
