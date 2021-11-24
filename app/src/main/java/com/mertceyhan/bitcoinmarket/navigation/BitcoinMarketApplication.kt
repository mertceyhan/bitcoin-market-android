package com.mertceyhan.bitcoinmarket.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mertceyhan.bitcoinmarket.features.market.ui.MarketScreen
import com.mertceyhan.bitcoinmarket.features.splash.ui.SplashScreen


@Composable
fun BitcoinMarketApplication() {
    val navigation = rememberNavController()

    NavHost(
        navController = navigation,
        startDestination = Screen.Splash.route
    ) {
        composable(
            route = Screen.Splash.route
        ) {
            SplashScreen(
                navigationController = navigation
            )
        }

        composable(
            route = Screen.Market.route
        ) {
            MarketScreen()
        }
    }
}