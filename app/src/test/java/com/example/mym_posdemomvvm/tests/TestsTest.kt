package com.example.mym_posdemomvvm.tests

import org.junit.Test
import com.google.common.truth.Truth.assertThat

class TestsTest {

    @Test
    fun getFormatedText() {
        val a = Tests.getFormatedText("J,J")
        assertThat(a).isEqualTo("J%J")
    }

    @Test
    fun extractNumberFromString() {
        val a  = Tests.extractNumberFromString("9123")
        assertThat(a).isEqualTo(9123)
    }

    @Test
    fun setOnWheelItemSelectedListener() {
    }

    @Test
    fun majorityElement() {
    }

    @Test
    fun getLongNumber() {
        val a: Long = 999999L * 99999
        val t = Tests.getLongNumber()
        assertThat(t).isEqualTo(a.toString())
    }

    @Test
    fun getNumberFormated() {
        val b: Double = 999999.99 * 999999 * 999
        val t = Tests.getNumberFormated(b)
        assertThat(t).isEqualTo("1234567890123456789")
    }

    @Test
    fun getNumberInCurrency() {
        val t = Tests.getNumberInCurrency(100000000.2)
        assertThat(t).isEqualTo("")
    }


}