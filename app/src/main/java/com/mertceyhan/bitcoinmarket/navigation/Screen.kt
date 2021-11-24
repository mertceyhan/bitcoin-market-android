package com.mertceyhan.bitcoinmarket.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Market : Screen("market")
}
