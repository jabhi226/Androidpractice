package com.example.mym_posdemomvvm.tests

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

object Tests {

    const val batchQty = 12
    const val batchLoose = 10
    const val divisor = 15

    fun setOnWheelItemSelectedListener(
        enteredQty: Int,
        enteredLoose: Int,
        STOP_NEGATIVE_SELL: Boolean,
        isLooseNotFinished: Boolean
    ): Boolean {
        return if (enteredQty != 0) {
            if (
                STOP_NEGATIVE_SELL
                && batchLoose < enteredLoose
                && (enteredQty == batchQty)
            ) {
                return false
            } else {
                true
            }
        } else {
            true
        }
    }

    fun majorityElement(nums: IntArray): Int {
        var num = -1
        nums.distinct()
            .forEach { n ->
                if (nums.count { n == it } > (nums.size / 2)) {
                    num = n
                }
            }
        return num
    }

    fun extractNumberFromString(string: String): Int {
        var number = 0
        if (string.length > 3) {
            number = string
                .subSequence(string.length - 4, string.length)
                .filter { it.isDigit() }
                .toString().toInt()

//            for (i in string.subSequence(string.length-3, string.length)){
//                if (!i.isDigit()){
//
//                }
//            }

//            for (i in string.length-3 until string.length) {
//                if (string[i].isDigit()){
//                    number *= 10
//                    number += string[i].digitToInt()
//                }
//            }
        }
        return number
    }

    fun getFormatedText(string: String): String {
//        val ab = string
//            .filter { it.isLetterOrDigit() || it.isWhitespace() }
//            .replace(" ", "%")
//            .uppercase()

        val aa = string
            .replace(("[^\\w\\s]").toRegex(), " ")
            .replace(" ", "%")
            .uppercase()
//
//        val aaa = StringBuffer()
//        val aab = string.forEach { it ->
//            if (!it.isLetterOrDigit()){
//                aaa.append(' ')
//            } else {
//                aaa.append(it)
//            }
//        }

        return aa.toString()
    }

    fun getLongNumber(): String {
        var a: Long = 999999
        a *= 999999
        return a.toString()
    }

    fun getNumberFormated(number: Double): String {
        return number.toBigDecimal().toPlainString()
    }

//    fun getNumberInCurrency(input: Double): String {
//        return java.text.NumberFormat.getNumberInstance().format(input)
////         "%,d".format(input)
//    }

    fun getNumberInCurrency(input: Double): String {
        return java.text.NumberFormat.getNumberInstance().format(input)
//         "%,d".format(input)
    }

    fun format1(num: Double): String {
        val a: DecimalFormat = (NumberFormat.getInstance(Locale.ENGLISH) as DecimalFormat)
        a.applyPattern("###,###.##")
        return a.format(num)
    }

    fun getPercentageDone(current: Double, size: Double): Double {
        return (current / size) * 100.0
    }

    fun isIsomorphic(s: String, t: String): Boolean {
        val firstString = arrayListOf<Char>()
        val secondString = arrayListOf<Char>()
        s.forEachIndexed { index, c ->
            if (firstString.contains(c)) {
                val i = firstString.indexOf(c)
                if (secondString[i] != t[index]) {
                    return false
                }
            } else if (secondString.contains(t[index])) {
                val i = secondString.indexOf(t[index])
                if (firstString[i] != s[index]) {
                    return false
                }
            }
            firstString.add(c)
            secondString.add(t[index])
        }

        return true
    }

    //    abhi
//    aaabbhii
    fun isLongPressedName(name: String, typed: String): Boolean {
        var currentIndex = 1
        var previousChar = typed[currentIndex-1]

        name.forEachIndexed { index, c ->
            var counter = true
            var isValid = false
            while (counter){
                if (typed.length > currentIndex){
                    if (c == typed[currentIndex] || (c == previousChar)) {
                        println(c)
                        previousChar = typed[currentIndex]
                        currentIndex++
                        isValid = true
                    } else {
                        counter = false
                    }
                } else if (typed.length == currentIndex) {
                    if (c == previousChar){
                        println(c)
                        isValid = true
                        counter = false
                    } else {
                        counter = false
                    }
                } else {
                    counter = false
                }
            }
            if (!isValid){
                return false
            }
        }
        return true
    }

    fun isPalindrome(s: String): Boolean {
//        val a = s.lowercase().filter {
//            it.isLetter()
//        }
        val a = s.toLowerCase().filter {
            it.isLetterOrDigit()
        }
        if (a.length > 1)
            for (i in 0..(a.length / 2)) {
                if (a[i] != a[a.length - i - 1]) {
                    return false
                }
            }
        return true
    }

    fun isValidWebsite(s: String): Boolean{
        print("----------> ")
        val split = s.split(":")
        if (split.size == 3){
            println("BASE: ${split[0]}:${split[1]} | PORT: ${split[2]}")
        }
        else if (split.size == 2){
            if (split[0].lowercase() == "http"){
                println("BASE: ${split[0]}:${split[1]} | PORT: 80")
            } else if (split[0].lowercase() == "https"){
                println("BASE: ${split[0]}:${split[1]} | PORT: 443")
            }
        }
        return true
    }

    fun isLong(s: String): Long{
        return s.toLong()
    }

    fun largestOdd(s: String): String{
        for (i in s.length-1 downTo 0){
            if (s[i].toString().toInt() % 2 == 1){
                return s.subSequence(0, i+1).toString()
            }
        }
        return ""
    }

    fun someCandy(candyType: IntArray): Int{

        print("[")
        for (i in -100 .. 100){
            print("$i, ")
        }
        print("]")
        val a = candyType.size / 2
        val b = candyType.distinct().size
        return if (b > a){
            a
        } else {
            b
        }
    }


    fun minOperations(s: String): Int {
        val firstChar = s[0].digitToInt()
        var count = 0
        for (i in 1 until s.length) {
            println("------------->>>>>>> $i")
            if (i % 2 == 0 && s[i].digitToInt() != firstChar){
                count++
            } else if (i % 2 == 1 && s[i].digitToInt() == firstChar){
                count++
            }
        }
        return count
    }







}