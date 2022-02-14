package com.example.mym_posdemomvvm.tests

import org.junit.Test
import com.google.common.truth.Truth.assertThat
import java.math.BigDecimal

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
        val b = BigDecimal.valueOf(1234567890.1234)
        val t = Tests.getNumberInCurrency(1234567890.12)
        assertThat(t).isEqualTo("1,234,567,890.12")
    }

    @Test
    fun format1() {
        val t = Tests.format1(1234567890.1234)
        assertThat(t).isEqualTo("1,234,567,890.12")
    }

    @Test
    fun getPercentageDone() {
        val t = Tests.getPercentageDone(2000000.0, 32795729.0)
        assertThat(t).isEqualTo(10.0)
    }

    @Test
    fun isIsomorphic() {
        val t = Tests.isIsomorphic("abcabcabcaaacbabcaaacb ", "xyzxyzxyzxxxzyxyzxxxzy ")
        assertThat(t).isEqualTo(true)
    }


}