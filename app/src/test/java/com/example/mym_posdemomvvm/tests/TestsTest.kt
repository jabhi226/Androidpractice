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

//    @Test
//    fun format1() {
//        val t = Tests.format1(1234567890.1234)
//        assertThat(t).isEqualTo("1,234,567,890.12")
//    }
//
//    @Test
//    fun getPercentageDone() {
//        val t = Tests.getPercentageDone(2000000.0, 32795729.0)
//        assertThat(t).isEqualTo(10.0)
//    }
//
//    @Test
//    fun isIsomorphic() {
//        val t = Tests.isIsomorphic("abcabcabcaaacbabcaaacb ", "xyzxyzxyzxxxzyxyzxxxzy ")
//        assertThat(t).isEqualTo(true)
//    }
//
//    @Test
//    fun isPalindrome() {
//        val t = Tests.isPalindrome("Ann e, I vote mo! re c-.ars race Rome to Vienna.")
//        assertThat(t).isEqualTo(true)
//    }
//
//    @Test
//    fun isLongPressedName() {
//        val t = Tests.isLongPressedName("abhii", "aaabhhi")
//        assertThat(t).isEqualTo(true)
//    }
//
//    @Test
//    fun isValidWebsite() {
//        // edge case http://3.7.14.246:6031/service/
////        val a = "http://pemedmapper.digihealth.in"
////        val a = "http://kabootar.instinctinnovations.com/service/"
////        val a = "https://youtube.com/digihealth"
////        val a = "http://192.168.137.127:8090"
////        val a = "https://3.7.14.246:6031"
////        val a = "https://saarthidev.digihealth.in"
//        val a = "https://3.7.14.246:6031"
//        val t = Tests.isValidWebsite(a)
//        assertThat(t).isTrue()
//    }
//
//    @Test
//    fun isLong(){
//        val t = Tests.isLong("0")
//        assertThat(t).isEqualTo(0)
//    }
//
//    @Test
//    fun largestOdd(){
//        val t = Tests.largestOdd("999999")
//        assertThat(t).isEqualTo("999999")
//    }
//
//    @Test
//    fun someCandy(){
//        val t = Tests.someCandy(intArrayOf(-100, -99, -98, -97, -96, -95, -94, -93, -92, -91, -90, -89, -88, -87, -86, -85, -84, -83, -82, -81, -80, -79, -78, -77, -76, -75, -74, -73, -72, -71, -70, -69, -68, -67, -66, -65, -64, -63, -62, -61, -60, -59, -58, -57, -56, -55, -54, -53, -52, -51, -50, -49, -48, -47, -46, -45, -44, -43, -42, -41, -40, -39, -38, -37, -36, -35, -34, -33, -32, -31, -30, -29, -28, -27, -26, -25, -24, -23, -22, -21, -20, -19, -18, -17, -16, -15, -14, -13, -12, -11, -10, -9, -8, -7, -6, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100))
//        assertThat(t).isEqualTo(3)
//    }
//
//    @Test
//    fun minOperations(){
//        val t = Tests.minOperations("10010100")
//        assertThat(t).isEqualTo(3)
//    }
//
//    @Test
//    fun checkStraightLine(){
//        val a = arrayListOf<IntArray>()
//        a.add(intArrayOf(0,0))
//        a.add(intArrayOf(0,1))
//        a.add(intArrayOf(0,-1))
//        val t = Tests.checkStraightLine(a.toTypedArray())
//        assertThat(t).isEqualTo(true)
//    }
//
//    @Test
//    fun compareVersion(){
////        If version1 < version2, return -1.
////        If version1 > version2, return 1.
////        Otherwise, return 0.
//        val t = Tests.compareVersion("2.5.0", "2.5.0")
//        assertThat(t).isEqualTo(0)
//    }
//
//    @Test
//    fun abc(){
//        val t = Tests.abc()
//        assertThat(t).isEqualTo("")
//    }
//
//    @Test
//    fun getDiscountFromMap(){
//        val outStanding = "cred=0.0, familyMemberBalance=0.0, patientDiscount=null, bal=0.0, loyaltyPoints=0.0"
//        val keyValuePairs: List<String> = outStanding.split(",")
//
//        val t = Tests.getDiscountFromMap(keyValuePairs)
//        assertThat(t).isEqualTo(10.0)
//    }
//
//    @Test
//    fun hammingWeight(){
//        val a = "11111111111111111111111111111111".toUInt(2).toInt()
//        val t = Tests.hammingWeight(a)
//        assertThat(t).isEqualTo(1)
//    }
//
//    @Test
//    fun makeGood() {
//        val a = "abBAcC"
//        val t = Tests.makeGood(a)
//        assertThat(t).isEqualTo("")
//    }
//
//    @Test
//    fun getDate(){
//        val a = "02/55"
//        val t = Tests.getDate(a)
//        assertThat(t).isEqualTo("01/02/2055")
//    }
//
//    @Test
//    fun sortSentence(){
//        val a = "Myself2 Me1 I4 and3"
//        val t = Tests.sortSentence(a)
//        assertThat(t).isEqualTo("Me Myself and I")
//    }
//
//    @Test
//    fun getRoundOff(){
//        val t = Tests.getRoundOff(7.905)
//        assertThat(t).isEqualTo(234.94)
//    }
//
//    @Test
//    fun getTotal(){
//        val t = Tests.getTotal(7.905)
//        assertThat(t).isEqualTo(657832.324)
//    }
//
//    @Test
//    fun groupThePeople(){
//        val a = intArrayOf(3,3,3,3,3,1,3)
//
//        val t = Tests.groupThePeople(a)
//
//        assertThat(t).isEqualTo(657832.324)
//
//    }
//
//    @Test
//    fun minPairSum(){
//        val a = intArrayOf(3,3,3,3,3,3)
//        val t = Tests.minPairSum(a)
//        assertThat(t).isEqualTo(6)
//    }
//
//    //0,
//    //2,4,5,6,2,3,4
//
//    @Test
//    fun calculation(){
//        val t = Tests.calculation()
//        assertThat(t).isEqualTo("")
//    }
//
//    @Test
//    fun countSubstrings(){
//        val t = Tests.countSubstrings("abccba")
//        assertThat(t).isEqualTo(8)
//    }
//
//    @Test
//    fun findMinNumOfCoins(){
//        val t = Tests.findMinNumOfCoins(203)
//        assertThat(t).isEqualTo(200)
//    }
//
//    @Test
//    fun medicineLevelDiscount(){
//        val t = Tests.medicineLevelDiscount()
//        assertThat(t).isNull()
//    }
//
//    @Test
//    fun emptyArray(){
//        val t = Tests.emptyArray()
//        assertThat(t).isNull()
//    }
//
//    @Test
//    fun somethingMinSwap(){
//        val s = "]]][[["
//        val t = Tests.somethingMinSwap(s)
//        assertThat(t).isEqualTo(0)
//    }
//
//    @Test
//    fun getMedicineMrpFromName(){
//        val t = Tests.getMedicineMrpFromName("CROCIN")
//        assertThat(t).isEqualTo("CROCIN")
//    }
//
//    @Test
//    fun scriptGenerator(){
//        val t = Tests.scriptGenerator()
//        assertThat(t).isEqualTo("CROCIN")
//    }
//
//    @Test
//    fun getDataTypeByValue(){
//        val t = Tests.getDataTypeByValue()
//        assertThat(t).isEqualTo("CROCIN")
//    }
//
//    @Test
//    fun convertColumnToRow(){
//        val t = Tests.convertColumnToRow()
//        assertThat(t).isEqualTo("CROCIN")
//    }

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
    fun rotate() {
        val array = arrayOf(
            intArrayOf(5,1,9,11),
            intArrayOf(2,4,8,10),
            intArrayOf(13,3,6,7),
            intArrayOf(15,14,12,16),
        )

        assertThat(
            Tests.rotate(array)
        ).isEqualTo(null)
    }

    @Test
    fun garbageCollection() {
        assertThat(
            Tests.garbageCollection(TestCases.array, TestCases.a)
        ).isEqualTo(21)
    }


    @Test
    fun t1() {
        assertThat(Tests.t1()).isEqualTo(21)
    }

    @Test
    fun findDuplicate() {
        assertThat(Tests.findDuplicate(intArrayOf(1,4,6,2,7,4))).isEqualTo(21)
    }

    @Test
    fun binarySearch() {
        assertThat(Tests.binarySearch(intArrayOf(1,4,5,7,9,12,15),5)).isEqualTo(21)
    }



    @Test
    fun topKFrequent() {
        val nums = intArrayOf(1, 1, 1, 1, 2, 2, 2, 3, 3, 4, 4, 4)
        val k = 3
        val t = Tests.topKFrequent(nums, k)
        assertThat(t).isEqualTo(intArrayOf(1, 2))

        val b = 10
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
    fun minPartitions() {
        val test = Tests.minPartitions(
            "8555555555"
        )
        assertThat(test).isEqualTo(8)
    }

    @Test
    fun minPartitiodeepestLeavesSumns() {
        val test = Tests.deepestLeavesSum(Tests.TreeNode(1))
        assertThat(test).isEqualTo(1)
    }

    @Test
    fun minOperations() {
        val test = Tests.minOperations(3)
        val a = assertThat(test)
        a.isEqualTo(3)
    }

    @Test
    fun permute() {
        val test = Tests.permute(intArrayOf(1,2,3,4))
        assertThat(test).isEqualTo(2)
    }

    @Test
    fun removeDuplicates() {
        val test = Tests.removeDuplicates(intArrayOf(1,1,1,2,2,3,3,3,3,4,4,5))
        assertThat(test).isEqualTo(2)
    }

    @Test
    fun gameOfLife() {
        //[[0,1,0],[0,0,1],[1,1,1],[0,0,0]]
        //[[1,1],[1,0]]
        val req = arrayOf(
            intArrayOf(0,1,0),
            intArrayOf(0,0,1),
            intArrayOf(1,1,1),
            intArrayOf(0,0,0),
        )
        val test = Tests.gameOfLife(req)
        assertThat(test).isEqualTo(2)
    }

    @Test
    fun increaseLetters() {
        val test = Tests.increaseLetters("abhishekz")
        assertThat(test).isEqualTo("mntuetqwl")
    }

    @Test
    fun removeDuplicateLetters() {
        val test = Tests.removeDuplicateLetters("abhishek")
        assertThat(test).isEqualTo(2)
    }

    @Test
    fun testLinkedIn() {
        val test = Tests.testLinkedIn()
        assertThat(test).isEqualTo(2)
    }

    @Test
    fun sortTheStudents() {
        //[[10,6,9,1],[7,5,11,2],[4,8,3,15]]
//        [3,4],[5,6]
        val q = arrayOf(
            intArrayOf(10,6,9,1),
            intArrayOf(7,5,11,2),
            intArrayOf(4,8,3,15),
        )
        val test = Tests.sortTheStudents(q, 2)
        assertThat(test).isEqualTo(2)
    }

}