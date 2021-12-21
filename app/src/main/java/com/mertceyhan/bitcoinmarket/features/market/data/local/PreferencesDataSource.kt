package com.mertceyhan.bitcoinmarket.features.market.data.local

import android.content.SharedPreferences
import javax.inject.Inject

class PreferencesDataSource @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {

    fun getLastMarketRequestTime(timeSpan: String): Long =
        sharedPreferences.getLong(timeSpan, 0L)

    fun setLastMarketRequestTime(timeSpan: String) {
        sharedPreferences.edit().apply {
            putLong(timeSpan, System.currentTimeMillis())
            apply()
        }
    }
}
