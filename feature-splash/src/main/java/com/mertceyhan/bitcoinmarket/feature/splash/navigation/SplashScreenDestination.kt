package com.mertceyhan.bitcoinmarket.feature.splash.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mertceyhan.bitcoinmarket.feature.splash.SplashScreen
import com.penguenlabs.core.navigation.Destination

object SplashScreenDestination : Destination {
    override val route: String = "splash_screen"
}

fun NavGraphBuilder.splashScreenGraph(onSplashScreenComplete: () -> Unit) {
    composable(route = SplashScreenDestination.route) {
        SplashScreen(onSplashScreenComplete = onSplashScreenComplete)
    }
}