package com.mertceyhan.bitcoinmarket.widget

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.glance.GlanceModifier
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.fillMaxSize
import androidx.glance.text.Text
import com.mertceyhan.bitcoinmarket.widget.callback.MarketGlanceCallback

class MarketWidget : GlanceAppWidget() {

    @Composable
    override fun Content() {
        Box(
            modifier = GlanceModifier.background(MaterialTheme.colors.surface)
                .clickable(actionRunCallback<MarketGlanceCallback>())
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Hello Btc")
        }
    }
}