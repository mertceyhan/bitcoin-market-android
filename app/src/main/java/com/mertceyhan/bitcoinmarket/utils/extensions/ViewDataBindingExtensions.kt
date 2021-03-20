package com.mertceyhan.bitcoinmarket.utils.extensions

import androidx.databinding.ViewDataBinding
import com.google.android.material.button.MaterialButton
import com.mertceyhan.R

fun ViewDataBinding.getButtonErrorAction(): MaterialButton? =
    root.findViewById(R.id.buttonErrorAction)
