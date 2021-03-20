package com.mertceyhan.bitcoinmarket.utils.extensions

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("isVisible")
fun View.isVisible(isVisible: Boolean?) {
    if (isVisible.isNull()) {
        return
    } else {
        visibility = if (isVisible.orFalse()) View.VISIBLE else View.GONE
    }
}
