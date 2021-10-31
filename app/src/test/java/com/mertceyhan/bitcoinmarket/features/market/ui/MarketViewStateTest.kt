package com.mertceyhan.bitcoinmarket.features.market.ui

import android.content.Context
import android.os.Build
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mertceyhan.R
import com.mertceyhan.bitcoinmarket.components.TimeRange
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

    private lateinit var marketScreenViewState: MarketScreenViewState

    @Before
    fun setUp() {
        marketScreenViewState = MarketScreenViewState(marketInformation)
    }

    @Test
    fun `when timespan is TIMESPAN_1DAYS, then getTimeRange() returns ONE_DAY `() {
        //given
        every { marketInformation.timespan } returns (MarketInformationTimespan.TIMESPAN_1DAYS)

        //when
        val result = marketScreenViewState.getTimeRange()

        //then
        result `should be` TimeRange.ONE_DAY
    }

    @Test
    fun `when timespan is TIMESPAN_7DAYS, then getTimeRange() returns SEVEN_DAYS `() {
        //given
        every { marketInformation.timespan } returns (MarketInformationTimespan.TIMESPAN_7DAYS)

        //when
        val result = marketScreenViewState.getTimeRange()

        //then
        result `should be` TimeRange.SEVEN_DAYS
    }

    @Test
    fun `when timespan is TIMESPAN_30DAYS, then getTimeRange() returns THIRTY_DAYS `() {
        //given
        every { marketInformation.timespan } returns (MarketInformationTimespan.TIMESPAN_30DAYS)

        //when
        val result = marketScreenViewState.getTimeRange()

        //then
        result `should be` TimeRange.THIRTY_DAYS
    }

    @Test
    fun `when timespan is TIMESPAN_60DAYS, then getTimeRange() returns SIXTY_DAYS `() {
        //given
        every { marketInformation.timespan } returns (MarketInformationTimespan.TIMESPAN_60DAYS)

        //when
        val result = marketScreenViewState.getTimeRange()

        //then
        result `should be` TimeRange.SIXTY_DAYS
    }

    @Test
    fun `when timespan is TIMESPAN_90DAYS, then getTimeRange() returns NINETY_DAYS `() {
        //given
        every { marketInformation.timespan } returns (MarketInformationTimespan.TIMESPAN_90DAYS)

        //when
        val result = marketScreenViewState.getTimeRange()

        //then
        result `should be` TimeRange.NINETY_DAYS
    }

    @Test
    fun `when timespan is TIMESPAN_1YEAR, then getTimeRange() returns ONE_YEAR `() {
        //given
        every { marketInformation.timespan } returns (MarketInformationTimespan.TIMESPAN_1YEAR)

        //when
        val result = marketScreenViewState.getTimeRange()

        //then
        result `should be` TimeRange.ONE_YEAR
    }

    @Test
    fun `when changeStatus is NEGATIVE, then isChangeStatusPositive() returns false `() {
        //given
        every { marketInformation.changeStatus } returns (MarketInformationChangeStatus.NEGATIVE)

        //when
        val result = marketScreenViewState.isChangeStatusPositive()

        //then
        result `should be` false
    }

    @Test
    fun `when changeStatus is POSITIVE, then isChangeStatusPositive() returns true `() {
        //given
        every { marketInformation.changeStatus } returns (MarketInformationChangeStatus.POSITIVE)

        //when
        val result = marketScreenViewState.isChangeStatusPositive()

        //then
        result `should be` true
    }

    @Test
    fun `when changeStatus is POSITIVE, then getColor returns green_500`() {
        // given
        every { marketInformation.changeStatus } returns MarketInformationChangeStatus.POSITIVE

        // when
        val result = marketScreenViewState.getColor(context)

        // then
        result `should be` context.getCompatColor(R.color.green_500)
    }

    @Test
    fun `when changeStatus is not POSITIVE, then getColor returns red_500`() {
        // given
        every { marketInformation.changeStatus } returns MarketInformationChangeStatus.NEGATIVE

        // when
        val result = marketScreenViewState.getColor(context)

        // then
        result `should be` context.getCompatColor(R.color.red_500)
    }

    @Test
    fun `when changeStatus is POSITIVE, then getBackground returns background_positive_chart`() {
        // given
        every { marketInformation.changeStatus } returns MarketInformationChangeStatus.POSITIVE

        // when
        val result = marketScreenViewState.getBackground(context)

        // then
        shadowOf(result).createdFromResId `should be` R.drawable.background_positive_chart
    }

    @Test
    fun `when changeStatus is not POSITIVE, then getBackground returns background_negative_chart`() {
        // given
        every { marketInformation.changeStatus } returns MarketInformationChangeStatus.NEGATIVE

        // when
        val result = marketScreenViewState.getBackground(context)

        // then
        shadowOf(result).createdFromResId `should be` R.drawable.background_negative_chart
    }
}
