package com.mertceyhan.bitcoinmarket.widget

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.datastore.preferences.core.Preferences
import androidx.glance.*
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.state.GlanceStateDefinition
import androidx.glance.state.PreferencesGlanceStateDefinition
import com.mertceyhan.R
import com.mertceyhan.bitcoinmarket.core.ui.theme.BitcoinMarketTheme
import com.mertceyhan.bitcoinmarket.utils.extensions.darkModeEnabled
import com.mertceyhan.bitcoinmarket.widget.receiver.MarketWidgetReceiver
import com.mertceyhan.bitcoinmarket.widget.ui.BitcoinWidget

class MarketWidget : GlanceAppWidget() {

    override val stateDefinition: GlanceStateDefinition<*> = PreferencesGlanceStateDefinition

    @Composable
    override fun Content() {

        val context = LocalContext.current
        val prefs = currentState<Preferences>()
        val currentPrice = prefs[MarketWidgetReceiver.currentPrice]
        val changeRate = prefs[MarketWidgetReceiver.changeRate]
        val isChangeRatePositive = prefs[MarketWidgetReceiver.isChangeRatePositive]

        val bitcoinWidgetUiState = BitcoinWidgetUiState(
            currentPrice = currentPrice ?: context.getString(R.string.fetch_alert_label),
            changeRate = changeRate ?: context.getString(R.string.empty_rate),
            isChangeRatePositive = isChangeRatePositive ?: true
        )

        BitcoinMarketTheme(darkTheme = context.darkModeEnabled()) {
            BitcoinWidget(bitcoinWidgetUiState)
        }
    }

}

data class BitcoinWidgetUiState(
    var currentPrice: String,
    var changeRate: String,
    var isChangeRatePositive: Boolean
)