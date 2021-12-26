package com.mertceyhan.bitcoinmarket.widget.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.*
import androidx.glance.action.clickable
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.layout.*
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.mertceyhan.R
import com.mertceyhan.bitcoinmarket.widget.callback.MarketGlanceCallback
import com.mertceyhan.bitcoinmarket.widget.callback.MarketRefreshCallback

@Composable
fun BitcoinWidget() {

    Column(
        modifier = GlanceModifier
            .width(170.dp).height(130.dp)
            .background(ImageProvider(R.drawable.background_widget))
            .clickable(actionRunCallback<MarketGlanceCallback>())
            .padding(8.dp)
    ) {
        BitcoinWidgetHeader()
        BitcoinWidgetBody()
        BitcoinFooterBody(true, "0.64%")
    }
}

@Composable
fun BitcoinWidgetHeader() {

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
                provider = ImageProvider(R.drawable.ic_refresh),
                contentDescription = "refresh",
                modifier = GlanceModifier.clickable(actionRunCallback<MarketRefreshCallback>())
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
        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp),
    )
}

@Composable
fun BitcoinFooterBody(isChangeRatePositive: Boolean, changeRate: String) {

    val rateBackground = if (isChangeRatePositive) {
        R.drawable.background_widget_positive_rate
    } else {
        R.drawable.background_widget_negative_rate
    }
    val rateArrow = if (isChangeRatePositive) {
        R.drawable.ic_arrow_positive_white
    } else {
        R.drawable.ic_arrow_negative_white
    }

    Row(
        modifier = GlanceModifier.fillMaxWidth().padding(top = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = GlanceModifier.background(rateBackground).padding(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                provider = ImageProvider(rateArrow),
                contentDescription = "refresh",
                modifier = GlanceModifier.size(11.dp, 11.dp)
            )
            Text(
                text = changeRate,
                style = TextStyle(
                    fontSize = 12.sp,
                    color = ColorProvider(R.color.white)
                ),
                modifier = GlanceModifier.padding(start = 4.dp)
            )
        }
        Row(
            horizontalAlignment = Alignment.End,
            modifier = GlanceModifier.fillMaxWidth()
        ) {
            Image(
                provider = ImageProvider(R.drawable.ic_app),
                contentDescription = "refresh",
                modifier = GlanceModifier.size(24.dp, 24.dp)
            )
        }
    }
}