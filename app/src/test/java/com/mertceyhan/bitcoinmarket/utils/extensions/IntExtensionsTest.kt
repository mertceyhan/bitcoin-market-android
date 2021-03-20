package com.mertceyhan.bitcoinmarket.utils.extensions

import com.mertceyhan.bitcoinmarket.utils.`should be`
import org.junit.Test

class IntExtensionsTest {

    @Test
    fun `when int is not null, then orZero() returns itself`() {
        // given
        val intFirst = 1
        val intSecond = 2

        // when
        val resultIntFirst = intFirst.orZero()
        val resultIntSecond = intSecond.orZero()

        // then
        resultIntFirst `should be` intFirst
        resultIntSecond `should be` intSecond
    }

    @Test
    fun `when int is null, then orZero() returns zero`() {
        // given
        val intNull: Int? = null

        // when
        val result = intNull.orZero()

        // then
        result `should be` 0
    }
}
