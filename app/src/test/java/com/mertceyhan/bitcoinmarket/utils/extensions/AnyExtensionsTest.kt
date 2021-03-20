package com.mertceyhan.bitcoinmarket.utils.extensions

import com.mertceyhan.bitcoinmarket.utils.`should be`
import org.junit.Test

class AnyExtensionsTest {

    @Test
    fun `when any is null, then isNull() returns true`() {
        // given
        val any: Any? = null

        // when
        val result = any.isNull()

        // then
        result `should be` true
    }

    @Test
    fun `when any is not null, then isNull() returns false`() {
        // given
        val any = Any()

        // when
        val result = any.isNull()

        // then
        result `should be` false
    }

    @Test
    fun `when any is null, then isNotNull() returns false`() {
        // given
        val any: Any? = null

        // when
        val result = any.isNotNull()

        // then
        result `should be` false
    }

    @Test
    fun `when any is not null, then isNotNull() returns true`() {
        // given
        val any = Any()

        // when
        val result = any.isNotNull()

        // then
        result `should be` true
    }
}
