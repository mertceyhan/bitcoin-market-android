package com.mertceyhan.bitcoinmarket.features.splash.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.mertceyhan.R
import com.mertceyhan.bitcoinmarket.core.ui.BaseFragment
import com.mertceyhan.databinding.FragmentSplashBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : BaseFragment<FragmentSplashBinding>() {

    override fun getLayoutId(): Int = R.layout.fragment_splash

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            delay(SPLASH_NAVIGATION_DELAY)
            navigateToMarketFragment()
        }
    }

    private fun navigateToMarketFragment() {
        findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToBitcoinMarketFragment())
    }

    companion object {
        private const val SPLASH_NAVIGATION_DELAY: Long = 1000
    }
}
