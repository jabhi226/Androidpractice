package com.example.mym_posdemomvvm.tests

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import java.math.BigDecimal

class TestsTest {

    @Test
    fun getFormatedText() {
        val a = Tests.getFormatedText("J,J")
        assertThat(a).isEqualTo("J%J")
    }

    @Test
    fun extractNumberFromString() {
        val a = Tests.extractNumberFromString("9123")
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
    fun isPalindrome() {
        val t = Tests.isPalindrome("Ann e, I vote mo! re c-.ars race Rome to Vienna.")
        assertThat(t).isEqualTo(true)
    }

    @Test
    fun isLongPressedName() {
        val t = Tests.isLongPressedName("abhii", "aaabhhi")
        assertThat(t).isEqualTo(true)
    }

    @Test
    fun longestCommonPrefix() {
        val a = arrayOf("ab", "a")
        val t = Tests.longestCommonPrefix(a)
        assertThat(t).isEqualTo("a")
    }

    @Test
    fun lengthOfLastWord() {
        val a = " "
        val t = Tests.lengthOfLastWord(a)
        assertThat(t).isEqualTo(0)
    }

    @Test
    fun isLongPressedName1() {
        val a = "alex"
        val b = "aaleeexx"
        val t = Tests.isLongPressedName(a, b)
        assertThat(t).isEqualTo(true)
    }

    @Test
    fun tribonacci() {
        val t = Tests.tribonacci(32)
        assertThat(t).isEqualTo(98950096)
    }

    @Test
    fun climbStairs() {
        val t = Tests.climbStairs(3)
        assertThat(t).isEqualTo(13)
    }


    @Test
    fun topKFrequent() {
        val nums = intArrayOf(1, 1, 1, 1, 2, 2, 2, 3, 3, 4, 4, 4)
        val k = 3
        val t = Tests.topKFrequent(nums, k)
        assertThat(t).isEqualTo(intArrayOf(1, 2))
    }

    @Test
    fun map() {
        val nums = intArrayOf(1, 1, 1, 1, 2, 2, 2, 3, 3, 4, 4, 4)
        Tests.map(nums, 2)
    }

    @Test
    fun pairInArray() {
        val array = intArrayOf(11, 15, 6, 8, 9, 10)
        assertThat(Tests.pairInArray(array, 16)).isTrue()
    }

    @Test
    fun findingUsersActiveMinutes() {
        val array = arrayOf(
            intArrayOf(0, 5),
            intArrayOf(1, 2),
            intArrayOf(0, 2),
            intArrayOf(0, 5),
            intArrayOf(1, 3)
        )

        assertThat(
            Tests.findingUsersActiveMinutes(array, 5)
        ).isEqualTo(intArrayOf(0, 2, 0, 0, 0))
    }


}






