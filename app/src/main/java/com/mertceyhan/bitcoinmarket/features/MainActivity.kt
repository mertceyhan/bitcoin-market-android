package com.mertceyhan.bitcoinmarket.features

import com.mertceyhan.R
import com.mertceyhan.bitcoinmarket.core.ui.BaseActivity
import com.mertceyhan.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getLayoutId(): Int = R.layout.activity_main
}
