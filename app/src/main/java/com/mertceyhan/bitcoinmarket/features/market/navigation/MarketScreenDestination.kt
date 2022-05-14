package com.mertceyhan.bitcoinmarket.features.market.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mertceyhan.bitcoinmarket.features.market.ui.MarketScreen
import com.penguenlabs.core.navigation.Destination

object MarketScreenDestination : Destination {
    override val route: String = "market_screen"
}

fun NavGraphBuilder.marketScreenGraph() {
    composable(route = MarketScreenDestination.route) {
        MarketScreen()
    }
}