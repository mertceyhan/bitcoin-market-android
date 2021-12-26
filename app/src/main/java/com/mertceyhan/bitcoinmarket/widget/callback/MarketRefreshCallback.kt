package com.mertceyhan.bitcoinmarket.widget.callback

import android.content.Context
import androidx.glance.GlanceId
import androidx.glance.action.ActionParameters
import androidx.glance.appwidget.action.ActionCallback
import com.mertceyhan.bitcoinmarket.widget.MarketWidget

class MarketRefreshCallback : ActionCallback {

    override suspend fun onRun(context: Context, glanceId: GlanceId, parameters: ActionParameters) {
        MarketWidget().update(context, glanceId)
    }
}