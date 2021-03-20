package com.mertceyhan.bitcoinmarket.utils.extensions

import android.content.res.Resources
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter

@BindingAdapter("drawableRes")
fun ImageView.drawableRes(@DrawableRes drawableRes: Int?) {
    if ((drawableRes.isNotNull()) and (drawableRes != Resources.ID_NULL)) {
        setImageDrawable(context.getCompatDrawable(drawableRes!!))
    }
}
