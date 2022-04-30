package com.mertceyhan.bitcoinmarket.theme

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ThemeTest {

    @Test
    fun `given isSystemInDarkTheme=true, then getColors() returns darkColors`() {
        //given
        val isSystemInDarkTheme = true
        //when
        val result = getColors(isSystemInDarkTheme)
        //then
        assertThat(result).isEqualTo(darkColors)
    }

    @Test
    fun `given isSystemInDarkTheme=false, then getColors() returns lightColors`() {
        //given
        val isSystemInDarkTheme = false
        //when
        val result = getColors(isSystemInDarkTheme)
        //then
        assertThat(result).isEqualTo(lightColors)
    }
}