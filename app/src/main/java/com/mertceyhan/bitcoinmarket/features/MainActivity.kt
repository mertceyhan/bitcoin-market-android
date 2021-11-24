package com.mertceyhan.bitcoinmarket.features

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.mertceyhan.bitcoinmarket.core.ui.theme.BitcoinMarketTheme
import com.mertceyhan.bitcoinmarket.navigation.BitcoinMarketApplication
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BitcoinMarketTheme {
                BitcoinMarketApplication()
            }
        }
    }
}