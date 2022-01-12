package com.mertceyhan.bitcoinmarket.widget.receiver

import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.state.PreferencesGlanceStateDefinition
import com.mertceyhan.bitcoinmarket.features.market.domain.model.MarketInformationChangeStatus
import com.mertceyhan.bitcoinmarket.features.market.domain.model.MarketInformationTimespan
import com.mertceyhan.bitcoinmarket.features.market.domain.usecase.MarketInformationUseCase
import com.mertceyhan.bitcoinmarket.widget.MarketWidget
import com.mertceyhan.bitcoinmarket.widget.callback.MarketRefreshCallback
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MarketWidgetReceiver : GlanceAppWidgetReceiver() {

    override val glanceAppWidget: GlanceAppWidget = MarketWidget()

    private val coroutineScope = MainScope()

    @Inject
    lateinit var marketInformationUseCase: MarketInformationUseCase

    private fun observeData(context: Context) {
        coroutineScope.launch {

            val marketInformation =
                marketInformationUseCase.getMarketInformation(MarketInformationTimespan.TIMESPAN_1DAYS)

            val glanceId =
                GlanceAppWidgetManager(context).getGlanceIds(MarketWidget::class.java).firstOrNull()

            glanceId?.let {
                updateAppWidgetState(context, PreferencesGlanceStateDefinition, it) { pref ->
                    pref.toMutablePreferences().apply {
                        this[currentPrice] =
                            marketInformation.currentPrice
                        this[changeRate] =
                            marketInformation.changeRate
                        this[isChangeRatePositive] =
                            marketInformation.changeStatus == MarketInformationChangeStatus.POSITIVE
                    }
                }
                glanceAppWidget.update(context, it)
            }
        }
    }

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        observeData(context)
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
        if (intent.action == MarketRefreshCallback.UPDATE_ACTION) {
            observeData(context)
        }
    }

    companion object {
        val currentPrice = stringPreferencesKey("currentPrice")
        val changeRate = stringPreferencesKey("changeRate")
        val isChangeRatePositive = booleanPreferencesKey("isChangeRatePositive")
    }

}