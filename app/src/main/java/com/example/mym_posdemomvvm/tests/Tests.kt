package com.example.mym_posdemomvvm.tests

import android.text.InputFilter
import android.text.Spannable
import android.text.Spanned
import java.math.BigDecimal
import java.math.BigInteger
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*
import kotlin.math.roundToLong

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
                if (nums.count { n == it } > (nums.size/2)){
                    num = n
                }
            }
        return num
    }

    fun extractNumberFromString(string: String): Int{
        var number = 0
        if (string.length > 3){
            number = string
                .subSequence(string.length-4, string.length)
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

    fun getFormatedText(string: String): String{
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

    fun getLongNumber(): String{
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

    fun format1(num: Double): String{
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
            if (firstString.contains(c)){
                val i = firstString.indexOf(c)
                if (secondString[i] != t[index]){
                    return false
                }
            } else if (secondString.contains(t[index])) {
                val i = secondString.indexOf(t[index])
                if (firstString[i] != s[index]){
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
        var i = 0
        name.forEachIndexed { index, c ->
            for (char in i .. typed.length) {
                if(c != typed[char]){
                    i = char
                    break
                }
            }
            if (c == typed[i]){

            } else {
                if (name.length < i)
                    i++
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
        for (i in 0..(a.length/2)) {
            if (a[i] != a[a.length-i-1]){
                return false
            }
        }
        return true
    }

















}