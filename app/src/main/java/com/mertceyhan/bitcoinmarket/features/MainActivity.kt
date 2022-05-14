package com.mertceyhan.bitcoinmarket.features

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.mertceyhan.bitcoinmarket.feature.splash.navigation.SplashScreenDestination
import com.mertceyhan.bitcoinmarket.feature.splash.navigation.splashScreenGraph
import com.mertceyhan.bitcoinmarket.features.market.navigation.MarketScreenDestination
import com.mertceyhan.bitcoinmarket.features.market.navigation.marketScreenGraph
import com.mertceyhan.bitcoinmarket.core.theme.BitcoinMarketTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BitcoinMarketTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = SplashScreenDestination.route
                ) {
                    splashScreenGraph(onSplashScreenComplete = {
                        navController.navigate(route = MarketScreenDestination.route) {
                            popUpTo(SplashScreenDestination.route) {
                                inclusive = true
                            }
                        }
                    })

                    marketScreenGraph()
                }
            }
        }
    }
}