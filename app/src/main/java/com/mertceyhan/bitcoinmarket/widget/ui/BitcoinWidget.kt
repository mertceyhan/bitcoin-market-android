package com.mertceyhan.bitcoinmarket.widget.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.glance.*
import androidx.glance.layout.*
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import com.mertceyhan.R
import com.mertceyhan.bitcoinmarket.utils.extensions.darkModeEnabled

@Composable
fun BitcoinWidget(){

    val context = LocalContext.current

    val backgroundDrawable = if (context.darkModeEnabled()) {
        ImageProvider(R.drawable.background_widget_dark)
    } else {
        ImageProvider(R.drawable.background_widget_light)
    }

    Column(
        modifier = GlanceModifier
            .width(170.dp).height(130.dp)
            .background(backgroundDrawable)
            .padding(8.dp)
    ) {
        BitcoinWidgetHeader()
        BitcoinWidgetBody()
    }
}

@Composable
fun BitcoinWidgetHeader() {

    val context = LocalContext.current

    val refreshDrawable = if (context.darkModeEnabled()) {
        ImageProvider(R.drawable.ic_refresh_white)
    } else {
        ImageProvider(R.drawable.ic_refresh_black)
    }

    Row(
        modifier = GlanceModifier.fillMaxWidth()
    ) {
        Image(
            provider = ImageProvider(R.drawable.ic_btc_flat),
            contentDescription = "btc"
        )
        Row(
            horizontalAlignment = Alignment.End,
            modifier = GlanceModifier.fillMaxWidth()
        ) {
            Image(
                provider = refreshDrawable,
                contentDescription = "refresh"
            )
        }
    }
}

@Composable
fun BitcoinWidgetBody() {

    val context = LocalContext.current

    Text(
        text = context.getString(R.string.bitcoin_label),
        modifier = GlanceModifier.fillMaxWidth().padding(top = 8.dp),
        style = TextStyle(fontWeight = FontWeight.Bold),
    )
    Text(
        text = "$48.823,08",
        modifier = GlanceModifier.fillMaxWidth().padding(top = 8.dp),
        style = TextStyle(fontWeight = FontWeight.Bold),
    )
}