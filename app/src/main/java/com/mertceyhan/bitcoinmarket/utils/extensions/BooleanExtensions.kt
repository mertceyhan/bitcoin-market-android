package com.mertceyhan.bitcoinmarket.utils.extensions

fun Boolean.doIfTrue(action: (() -> Unit)? = null) {
    if (this) {
        action?.invoke()
    }
}

fun Boolean?.orFalse() = this ?: false
