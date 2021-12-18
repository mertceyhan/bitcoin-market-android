package com.mertceyhan.bitcoinmarket.features.market.data.local

import android.content.SharedPreferences
import javax.inject.Inject

class PreferencesDataSource @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {

    fun getLastMarketRequestTime(key: String): Long =
        sharedPreferences.getLong(key, 0L)

    fun setLastMarketRequestTime(key: String) {
        sharedPreferences.edit().apply {
            putLong(key, System.currentTimeMillis())
            apply()
        }
    }
}
