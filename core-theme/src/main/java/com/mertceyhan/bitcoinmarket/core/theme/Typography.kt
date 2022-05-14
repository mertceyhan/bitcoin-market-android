package com.mertceyhan.bitcoinmarket.core.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.mertceyhan.bitcoinmarket.theme.R

private val redHatDisplayFontFamily = FontFamily(
    Font(R.font.red_hat_display_regular),
    Font(R.font.red_hat_display_medium, FontWeight.Medium),
    Font(R.font.red_hat_display_bold, FontWeight.Bold),
    Font(R.font.red_hat_display_black, FontWeight.Black)
)

internal val typography = Typography(
    defaultFontFamily = redHatDisplayFontFamily,
    h1 = TextStyle(fontSize = 64.sp, fontWeight = FontWeight.Black),
    h2 = TextStyle(fontSize = 48.sp, fontWeight = FontWeight.Black),
    h3 = TextStyle(fontSize = 36.sp, fontWeight = FontWeight.Bold),
    h4 = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
    h5 = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
    h6 = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold),
    body1 = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold),
    body2 = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Medium),
    subtitle1 = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold),
    subtitle2 = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Medium),
    button = TextStyle(fontWeight = FontWeight.Medium),
    overline = TextStyle(fontWeight = FontWeight.Medium),
)