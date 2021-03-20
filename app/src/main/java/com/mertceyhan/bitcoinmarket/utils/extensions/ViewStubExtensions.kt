package com.mertceyhan.bitcoinmarket.utils.extensions

import android.view.ViewStub

fun ViewStub.inflate(inflate: Boolean) {
    if (inflate) {
        inflate()
    }
}
