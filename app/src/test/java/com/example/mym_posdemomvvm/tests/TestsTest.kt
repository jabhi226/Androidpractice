package com.example.mym_posdemomvvm.tests

import org.junit.Test
import com.google.common.truth.Truth.assertThat
import java.math.BigDecimal
import kotlin.random.Random

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
    fun isValidWebsite() {
        // edge case http://3.7.14.246:6031/service/
//        val a = "http://pemedmapper.digihealth.in"
//        val a = "http://kabootar.instinctinnovations.com/service/"
//        val a = "https://youtube.com/digihealth"
//        val a = "http://192.168.137.127:8090"
//        val a = "https://3.7.14.246:6031"
//        val a = "https://saarthidev.digihealth.in"
        val a = "https://3.7.14.246:6031"
        val t = Tests.isValidWebsite(a)
        assertThat(t).isTrue()
    }

    @Test
    fun isLong(){
        val t = Tests.isLong("0")
        assertThat(t).isEqualTo(0)
    }

    @Test
    fun largestOdd(){
        val t = Tests.largestOdd("999999")
        assertThat(t).isEqualTo("999999")
    }

    @Test
    fun someCandy(){
        val t = Tests.someCandy(intArrayOf(-100, -99, -98, -97, -96, -95, -94, -93, -92, -91, -90, -89, -88, -87, -86, -85, -84, -83, -82, -81, -80, -79, -78, -77, -76, -75, -74, -73, -72, -71, -70, -69, -68, -67, -66, -65, -64, -63, -62, -61, -60, -59, -58, -57, -56, -55, -54, -53, -52, -51, -50, -49, -48, -47, -46, -45, -44, -43, -42, -41, -40, -39, -38, -37, -36, -35, -34, -33, -32, -31, -30, -29, -28, -27, -26, -25, -24, -23, -22, -21, -20, -19, -18, -17, -16, -15, -14, -13, -12, -11, -10, -9, -8, -7, -6, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100))
        assertThat(t).isEqualTo(3)
    }

    @Test
    fun minOperations(){
        val t = Tests.minOperations("10010100")
        assertThat(t).isEqualTo(3)
    }

    @Test
    fun checkStraightLine(){
        val a = arrayListOf<IntArray>()
        a.add(intArrayOf(0,0))
        a.add(intArrayOf(0,1))
        a.add(intArrayOf(0,-1))
        val t = Tests.checkStraightLine(a.toTypedArray())
        assertThat(t).isEqualTo(true)
    }

    @Test
    fun compareVersion(){
//        If version1 < version2, return -1.
//        If version1 > version2, return 1.
//        Otherwise, return 0.
        val t = Tests.compareVersion("2.5.0", "2.5.0")
        assertThat(t).isEqualTo(0)
    }

}