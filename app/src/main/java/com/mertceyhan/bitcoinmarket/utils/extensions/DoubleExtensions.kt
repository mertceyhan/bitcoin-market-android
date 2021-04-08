package com.mertceyhan.bitcoinmarket.utils.extensions

import java.text.NumberFormat
import java.util.Locale

fun Double?.orZero() = this ?: 0.0

fun Double?.toCurrency(locale: Locale = Locale("en", "US")): String = NumberFormat
    .getCurrencyInstance(locale)
    .format(this.orZero())
    .orEmpty()

fun Double.changeRateOf(number: Double): Double =
    (((number.minus(this)).div(this)).times(100)).round(2)

fun Double.round(decimals: Int = 2): Double =
    "%.${decimals}f".format(Locale("en", "US"), this).toDouble()
