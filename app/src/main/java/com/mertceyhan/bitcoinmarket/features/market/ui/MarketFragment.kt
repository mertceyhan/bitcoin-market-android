package com.mertceyhan.bitcoinmarket.features.market.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.mertceyhan.R
import com.mertceyhan.bitcoinmarket.base.ui.BaseFragment
import com.mertceyhan.bitcoinmarket.base.ui.LayoutViewState
import com.mertceyhan.bitcoinmarket.features.market.domain.model.MarketInformationTimespan
import com.mertceyhan.bitcoinmarket.utils.extensions.doIfTrue
import com.mertceyhan.bitcoinmarket.utils.extensions.getButtonErrorAction
import com.mertceyhan.bitcoinmarket.utils.extensions.inflate
import com.mertceyhan.bitcoinmarket.utils.extensions.isNull
import com.mertceyhan.databinding.FragmentMarketBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MarketFragment : BaseFragment<FragmentMarketBinding>() {

    private val marketViewViewModel: MarketViewModel by viewModels()

    override fun getLayoutId(): Int = R.layout.fragment_market

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpView()
        setUpViewModel()
        savedInstanceState.isNull().doIfTrue { getMarketInformation() }
    }

    private fun setUpView() {
        with(binding) {
            lineChart.apply {
                description.isEnabled = false
                isDragEnabled = false
                xAxis.isEnabled = false
                axisLeft.setDrawAxisLine(false)
                axisRight.isEnabled = false
                legend.isEnabled = false
                setTouchEnabled(false)
                setScaleEnabled(false)
                setDrawGridBackground(false)
                setDrawBorders(false)
                invalidate()
            }

            swipeRefreshLayout.setOnRefreshListener {
                getMarketInformationWithLastTimespan()
            }

            layoutError.setOnInflateListener { _, _ ->
                getButtonErrorAction()?.setOnClickListener {
                    getMarketInformationWithLastTimespan()
                }
            }

            chip1d.setOnClickListener {
                getMarketInformation(MarketInformationTimespan.TIMESPAN_1DAYS)
            }

            chip7d.setOnClickListener {
                getMarketInformation(MarketInformationTimespan.TIMESPAN_7DAYS)
            }

            chip30d.setOnClickListener {
                getMarketInformation(MarketInformationTimespan.TIMESPAN_30DAYS)
            }

            chip60d.setOnClickListener {
                getMarketInformation(MarketInformationTimespan.TIMESPAN_60DAYS)
            }

            chip90d.setOnClickListener {
                getMarketInformation(MarketInformationTimespan.TIMESPAN_90DAYS)
            }

            chip1y.setOnClickListener {
                getMarketInformation(MarketInformationTimespan.TIMESPAN_1YEAR)
            }
        }
    }

    private fun inflateLayoutError(layoutViewState: LayoutViewState) {
        binding.layoutError.viewStub?.inflate(layoutViewState.isError())
    }

    private fun setUpViewModel() {
        with(marketViewViewModel) {
            getLayoutViewStateLiveData().observe(
                viewLifecycleOwner,
                { layoutViewState ->
                    binding.layoutViewState = layoutViewState
                    binding.executePendingBindings()
                    inflateLayoutError(layoutViewState)
                }
            )

            getMarketViewStateLiveData().observe(
                viewLifecycleOwner,
                { marketViewState ->
                    binding.viewState = marketViewState
                    binding.executePendingBindings()
                }
            )
        }
    }

    private fun getMarketInformation(
        timespan: MarketInformationTimespan? = MarketInformationTimespan.TIMESPAN_30DAYS
    ) {
        marketViewViewModel.getMarketInformation(
            timespan = timespan ?: MarketInformationTimespan.TIMESPAN_30DAYS
        )
    }

    private fun getMarketInformationWithLastTimespan() {
        getMarketInformation(binding.viewState?.marketInformation?.timespan)
    }
}
