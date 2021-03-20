package com.mertceyhan.bitcoinmarket.utils.extensions

import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

@BindingAdapter("isRefreshing")
fun SwipeRefreshLayout.isRefreshing(isRefreshing: Boolean) {
    this.isRefreshing = isRefreshing
}
