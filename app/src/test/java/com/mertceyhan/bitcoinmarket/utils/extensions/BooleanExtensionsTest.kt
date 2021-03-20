package com.mertceyhan.bitcoinmarket.utils.extensions

import com.mertceyhan.bitcoinmarket.utils.`should be`
import org.junit.Test

class BooleanExtensionsTest {

    @Test
    fun `when boolean is true or false, then orFalse() returns itself`() {
        // given
        val booleanTrue = true
        val booleanFalse = false

        // when
        val resultBooleanTrue = booleanTrue.orFalse()
        val resultBooleanFalse = booleanFalse.orFalse()

        // then
        resultBooleanTrue `should be` booleanTrue
        resultBooleanFalse `should be` booleanFalse
    }

    @Test
    fun `when boolean is null, then orFalse() returns false`() {
        // given
        val booleanNull: Boolean? = null

        // when
        val result = booleanNull.orFalse()

        // then
        result `should be` false
    }
}
