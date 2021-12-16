package com.mertceyhan.bitcoinmarket.widget

import androidx.compose.runtime.Composable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.text.Text

class MarketWidget : GlanceAppWidget() {

    @Composable
    override fun Content() {
        Text(text = "Hello Btc")
    }
}