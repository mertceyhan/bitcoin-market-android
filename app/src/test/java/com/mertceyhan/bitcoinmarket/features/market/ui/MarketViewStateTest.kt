package com.mertceyhan.bitcoinmarket.features.market.ui

import android.content.Context
import android.os.Build
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mertceyhan.R
import com.mertceyhan.bitcoinmarket.features.market.domain.model.MarketInformation
import com.mertceyhan.bitcoinmarket.features.market.domain.model.MarketInformationChangeStatus
import com.mertceyhan.bitcoinmarket.features.market.domain.model.MarketInformationTimespan
import com.mertceyhan.bitcoinmarket.utils.`should be`
import com.mertceyhan.bitcoinmarket.utils.extensions.getCompatColor
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config

@Config(sdk = [Build.VERSION_CODES.P])
@RunWith(AndroidJUnit4::class)
class MarketViewStateTest {

    private val context = ApplicationProvider.getApplicationContext<Context>()

    private val marketInformation = mockk<MarketInformation>()

    private lateinit var marketViewState: MarketViewState

    @Before
    fun setUp() {
        marketViewState = MarketViewState(marketInformation)
    }

    @Test
    fun `when changeStatus is POSITIVE, then getChangeStatusIcon returns ic_arrow_positive`() {
        // given
        every { marketInformation.changeStatus } returns MarketInformationChangeStatus.POSITIVE

        // when
        val result = marketViewState.getChangeStatusIcon()

        // then
        result `should be` R.drawable.ic_arrow_positive
    }

    @Test
    fun `when changeStatus is not POSITIVE, then getChangeStatusIcon returns ic_arrow_negative`() {
        // given
        every { marketInformation.changeStatus } returns MarketInformationChangeStatus.NEGATIVE

        // when
        val result = marketViewState.getChangeStatusIcon()

        // then
        result `should be` R.drawable.ic_arrow_negative
    }

    @Test
    fun `when timespan is TIMESPAN_1DAYS, then isChip1dChecked returns true`() {
        // given
        every { marketInformation.timespan } returns MarketInformationTimespan.TIMESPAN_1DAYS

        // when
        val result = marketViewState.isChip1dChecked()

        // then
        result `should be` true
    }

    @Test
    fun `when timespan is not TIMESPAN_1DAYS, then isChip1dChecked returns false`() {
        // given
        every { marketInformation.timespan } returns MarketInformationTimespan.TIMESPAN_30DAYS

        // when
        val result = marketViewState.isChip1dChecked()

        // then
        result `should be` false
    }

    @Test
    fun `when timespan is TIMESPAN_7DAYS, then isChip7dChecked returns true`() {
        // given
        every { marketInformation.timespan } returns MarketInformationTimespan.TIMESPAN_7DAYS

        // when
        val result = marketViewState.isChip7dChecked()

        // then
        result `should be` true
    }

    @Test
    fun `when timespan is not TIMESPAN_7DAYS, then isChip7dChecked returns false`() {
        // given
        every { marketInformation.timespan } returns MarketInformationTimespan.TIMESPAN_1DAYS

        // when
        val result = marketViewState.isChip7dChecked()

        // then
        result `should be` false
    }

    @Test
    fun `when timespan is TIMESPAN_30DAYS, then isChip30dChecked returns true`() {
        // given
        every { marketInformation.timespan } returns MarketInformationTimespan.TIMESPAN_30DAYS

        // when
        val result = marketViewState.isChip30dChecked()

        // then
        result `should be` true
    }

    @Test
    fun `when timespan is not TIMESPAN_30DAYS, then isChip30dChecked returns false`() {
        // given
        every { marketInformation.timespan } returns MarketInformationTimespan.TIMESPAN_1DAYS

        // when
        val result = marketViewState.isChip30dChecked()

        // then
        result `should be` false
    }

    @Test
    fun `when timespan is TIMESPAN_60DAYS, then isChip60dChecked returns true`() {
        // given
        every { marketInformation.timespan } returns MarketInformationTimespan.TIMESPAN_60DAYS

        // when
        val result = marketViewState.isChip60dChecked()

        // then
        result `should be` true
    }

    @Test
    fun `when timespan is not TIMESPAN_60DAYS, then isChip60dChecked returns false`() {
        // given
        every { marketInformation.timespan } returns MarketInformationTimespan.TIMESPAN_1DAYS

        // when
        val result = marketViewState.isChip60dChecked()

        // then
        result `should be` false
    }

    @Test
    fun `when timespan is TIMESPAN_90DAYS, then isChip90dChecked returns true`() {
        // given
        every { marketInformation.timespan } returns MarketInformationTimespan.TIMESPAN_90DAYS

        // when
        val result = marketViewState.isChip90dChecked()

        // then
        result `should be` true
    }

    @Test
    fun `when timespan is not TIMESPAN_90DAYS, then isChip90dChecked returns false`() {
        // given
        every { marketInformation.timespan } returns MarketInformationTimespan.TIMESPAN_1DAYS

        // when
        val result = marketViewState.isChip90dChecked()

        // then
        result `should be` false
    }

    @Test
    fun `when timespan is TIMESPAN_1YEAR, then isChip1yChecked returns true`() {
        // given
        every { marketInformation.timespan } returns MarketInformationTimespan.TIMESPAN_1YEAR

        // when
        val result = marketViewState.isChip1yChecked()

        // then
        result `should be` true
    }

    @Test
    fun `when timespan is not TIMESPAN_1YEAR, then isChip1yChecked returns false`() {
        // given
        every { marketInformation.timespan } returns MarketInformationTimespan.TIMESPAN_1DAYS

        // when
        val result = marketViewState.isChip1yChecked()

        // then
        result `should be` false
    }

    @Test
    fun `when changeStatus is POSITIVE, then getColor returns green_500`() {
        // given
        every { marketInformation.changeStatus } returns MarketInformationChangeStatus.POSITIVE

        // when
        val result = marketViewState.getColor(context)

        // then
        result `should be` context.getCompatColor(R.color.green_500)
    }

    @Test
    fun `when changeStatus is not POSITIVE, then getColor returns red_500`() {
        // given
        every { marketInformation.changeStatus } returns MarketInformationChangeStatus.NEGATIVE

        // when
        val result = marketViewState.getColor(context)

        // then
        result `should be` context.getCompatColor(R.color.red_500)
    }

    @Test
    fun `when changeStatus is POSITIVE, then getBackground returns background_positive_chart`() {
        // given
        every { marketInformation.changeStatus } returns MarketInformationChangeStatus.POSITIVE

        // when
        val result = marketViewState.getBackground(context)

        // then
        shadowOf(result).createdFromResId `should be` R.drawable.background_positive_chart
    }

    @Test
    fun `when changeStatus is not POSITIVE, then getBackground returns background_negative_chart`() {
        // given
        every { marketInformation.changeStatus } returns MarketInformationChangeStatus.NEGATIVE

        // when
        val result = marketViewState.getBackground(context)

        // then
        shadowOf(result).createdFromResId `should be` R.drawable.background_negative_chart
    }
}
