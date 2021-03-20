package com.mertceyhan.bitcoinmarket.utils.extensions

import android.content.Context
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

fun Context.getCompatColor(@ColorRes id: Int) = ContextCompat.getColor(this, id)

fun Context.getCompatDrawable(@DrawableRes id: Int) = ContextCompat.getDrawable(this, id)
