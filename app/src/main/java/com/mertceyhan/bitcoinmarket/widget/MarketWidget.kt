package com.mertceyhan.bitcoinmarket.widget

import androidx.compose.runtime.Composable
import androidx.glance.*
import androidx.glance.appwidget.GlanceAppWidget
import com.mertceyhan.bitcoinmarket.core.ui.theme.BitcoinMarketTheme
import com.mertceyhan.bitcoinmarket.utils.extensions.darkModeEnabled
import com.mertceyhan.bitcoinmarket.widget.ui.BitcoinWidget

class MarketWidget : GlanceAppWidget() {

    @Composable
    override fun Content() {

        val context = LocalContext.current

        BitcoinMarketTheme(darkTheme = context.darkModeEnabled()) {
            BitcoinWidget()
        }
    }
}