package com.mertceyhan.bitcoinmarket.widget

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.glance.*
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.layout.*
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import com.mertceyhan.R
import com.mertceyhan.bitcoinmarket.widget.callback.MarketGlanceCallback

class MarketWidget : GlanceAppWidget() {

    @Composable
    override fun Content() {

        val context = LocalContext.current

        Column(
            modifier = GlanceModifier
                .fillMaxSize()
                .background(color = Color.DarkGray)
                .padding(8.dp)
        ) {
            Row(
                modifier = GlanceModifier.padding(top = 10.dp).fillMaxWidth()
            ) {
                Image(
                    provider = ImageProvider(R.drawable.ic_btc_flat),
                    contentDescription = "btc"
                )
                Image(
                    provider = ImageProvider(R.drawable.ic_refresh),
                    contentDescription = "refresh",
                )
            }
            Text(
                text = context.getString(R.string.bitcoin_label),
                modifier = GlanceModifier.fillMaxWidth(),
                style = TextStyle(fontWeight = FontWeight.Bold),
            )
        }
    }
}