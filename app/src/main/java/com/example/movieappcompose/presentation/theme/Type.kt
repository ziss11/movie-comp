package com.example.movieappcompose.presentation.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.movieappcompose.R

val ProductSans = FontFamily(
    Font(R.font.productsans_regular),
    Font(R.font.productsans_medium, FontWeight.Medium),
    Font(R.font.productsans_bold, FontWeight.Bold),
)

val Typography = Typography(
    h5 = TextStyle(
        fontFamily = ProductSans,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp
    ),
    h6 = TextStyle(
        fontFamily = ProductSans,
        fontWeight = FontWeight.SemiBold,
        fontSize = 19.sp
    ),
    body1 = TextStyle(
        fontFamily = ProductSans,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    body2 = TextStyle(
        fontFamily = ProductSans,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    button = TextStyle(
        fontFamily = ProductSans,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = ProductSans,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
)