package com.mertceyhan.bitcoinmarket.widget.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.*
import androidx.glance.action.actionStartActivity
import androidx.glance.action.clickable
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.layout.*
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.mertceyhan.R
import com.mertceyhan.bitcoinmarket.features.MainActivity
import com.mertceyhan.bitcoinmarket.widget.callback.MarketRefreshCallback

@Composable
fun BitcoinWidget(bitcoinWidgetUiState: BitcoinWidgetUiState) {

    Column(
        modifier = GlanceModifier
            .width(170.dp).height(130.dp)
            .background(ImageProvider(R.drawable.background_widget))
            .clickable(actionStartActivity(activity = MainActivity::class.java))
            .padding(8.dp)
    ) {
        BitcoinWidgetHeader()
        BitcoinWidgetBody(bitcoinWidgetUiState.currentPrice)
        BitcoinFooterBody(
            bitcoinWidgetUiState.isChangeRatePositive,
            bitcoinWidgetUiState.changeRate,
            bitcoinWidgetUiState.getRateBackground(),
            bitcoinWidgetUiState.getRateArrow()
        )
    }
}

@Composable
fun BitcoinWidgetHeader() {

    val context = LocalContext.current

    Row(
        modifier = GlanceModifier.fillMaxWidth()
    ) {
        Image(
            provider = ImageProvider(R.drawable.ic_btc_flat),
            contentDescription = context.getString(R.string.btc_description)
        )
        Row(
            horizontalAlignment = Alignment.End,
            modifier = GlanceModifier.fillMaxWidth()
        ) {
            Image(
                provider = ImageProvider(R.drawable.ic_refresh),
                contentDescription = context.getString(R.string.refresh_description),
                modifier = GlanceModifier.clickable(actionRunCallback<MarketRefreshCallback>())
            )
        }
    }
}

@Composable
fun BitcoinWidgetBody(currentPrice: String) {

    val context = LocalContext.current

    Text(
        text = context.getString(R.string.bitcoin_label),
        modifier = GlanceModifier.fillMaxWidth().padding(top = 8.dp),
        style = TextStyle(fontWeight = FontWeight.Bold),
    )
    Text(
        text = currentPrice,
        modifier = GlanceModifier.fillMaxWidth().padding(top = 8.dp),
        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp),
    )
}

@Composable
fun BitcoinFooterBody(
    isChangeRatePositive: Boolean,
    changeRate: String,
    rateBackground: Int,
    rateArrow: Int
) {

    val context = LocalContext.current

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
                contentDescription = context.getString(R.string.refresh_description),
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
                contentDescription = context.getString(R.string.refresh_description),
                modifier = GlanceModifier.size(24.dp, 24.dp)
            )
        }
    }
}