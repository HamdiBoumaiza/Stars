package com.hb.stars.utils

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ExtensionFunctionsTest {

    @Test
    fun verifyUrlConversion() {
        assertThat(("http://www.google.com").convertUrlToHttps()).matches("https://www.google.com")
    }

    @Test
    fun verifyCmToFeetConversion() {
        assertThat(("170").convertCmToFeet()).matches("5' 7''")
    }

    @Test
    fun verifyHasValue() {
        assertThat(("Undefined").hasValue()).isFalse()
    }
}