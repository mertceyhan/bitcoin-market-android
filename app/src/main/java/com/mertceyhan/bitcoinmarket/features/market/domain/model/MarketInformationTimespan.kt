package com.mertceyhan.bitcoinmarket.features.market.domain.model

enum class MarketInformationTimespan(val value: String) {
    TIMESPAN_1DAYS("1days"),
    TIMESPAN_7DAYS("7days"),
    TIMESPAN_30DAYS("30days"),
    TIMESPAN_60DAYS("60days"),
    TIMESPAN_90DAYS("90days"),
    TIMESPAN_1YEAR("365days");
}
