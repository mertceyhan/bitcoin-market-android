package com.mertceyhan.bitcoinmarket.core.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.mertceyhan.bitcoinmarket.core.ui.theme.BitcoinMarketTheme

abstract class BaseComposeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(context = requireContext()).apply {
            setContent {
                BitcoinMarketTheme {
                    this@BaseComposeFragment.Content()
                }
            }
        }
    }

    @Composable
    abstract fun Content()
}