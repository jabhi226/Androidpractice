package com.example.mym_posdemomvvm.tests

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

    fun getNumberInCurrency(input: Double): String {
        return java.text.NumberFormat.getNumberInstance().format(input)
//         "%,d".format(input)
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


    fun longestCommonPrefix(strs: Array<String>): String {
//        var count = 0
//        var counter = true
//        while (counter){
//            val current = strs[count][count]
//            for (i in strs.indices){
//                if (strs[i].length >= count){
//                    counter = false
//                    break
//                } else if (strs[i][count] != current){
//                    return strs[count].substring(0, count)
//                }
//            }
//            count++
//        }
        val a = strs[0]
        for (i in a.indices) {
            val c = a[i]
            for (j in strs.indices) {
//                if (strs[j].length < i){
//                    return
//                }
                if (strs[j].length < i && strs[j][i] != c) {
                    return strs[i].substring(0, i)
                }
            }
        }
        return a
    }


    fun lengthOfLastWord(s: String): Int {
        return s.trim().let { it.lastIndex - it.lastIndexOf(' ') }
    }

//    "saeed", typed = "ssaaedd"

    fun isLongPressedName(name: String, typed: String): Boolean {

        var currentIndex = 1
        val maxLength = name.length

        for (c in name) {
            println("$c | $currentIndex")
            if (currentIndex < maxLength && c == typed[currentIndex]) {
                currentIndex++
            } else if (c == typed[currentIndex - 1]) {
                currentIndex++
            } else {
                return false
            }
        }
        return true
    }

    // 0  1  1  2  4  7  13
    fun tribonacci(n: Int): Int {
        val list = Array(n) { -1 }
        when (n) {
            0 -> {
                return 0
            }
            1 -> {
                return 1
            }
            2 -> {
                return 1
            }
            else -> {
                list[0] = 0
                list[1] = 1
                list[2] = 1
            }
        }
        for (i in 3..n) {
            list[i] = list[i - 1] + list[i - 2] + list[i - 3]
        }
        list.forEach {
            println("--> $it")
        }
        return list.last()
    }


    //    1 1 1 1 1 1
//    1 1 1 1 2
//    1 1 1 2 1
//    1 1 2 1 1
//    1 2 1 1 1
//    2 1 1 1 1
//    1 1 2 2
//    1 2
    fun climbStairs(n: Int): Int {
        val list = Array(n) { -1 }
        when (n) {
            1 -> {
                return 1
            }
            else -> {
                list[0] = 1
                list[1] = 2
            }
        }
        for (i in 2 until n) {
            list[i] = list[i - 1] + list[i - 2]
        }
        list.forEach {
            println("--> $it")
        }
        return list.last()
    }

    fun topKFrequent(nums: IntArray, k: Int): IntArray {
        var list = Array(k) { -1 }
        nums.distinct().forEach { num ->
            val a = getCount(nums, num)
            val b = getCount(nums, list[0])
            println("$num | $a | ${list[0]} | $b")
            if (a >= b) {
                println("-----> $num")
                list[0] = num
            }
            list = list.sortedArray()
        }
        return list.toIntArray()
    }

    private fun getCount(list: IntArray, num: Int): Int {
        return list.filter { it == num && it != -1 }.size
    }

    fun map(nums: IntArray, k: Int) {
        var list = Array(k) { -1 }

        val map = mutableMapOf<Int, Int>()
        nums.distinct().forEach { num ->
            map.put(nums.filter { it == num }.size, num)
        }
        println(map)

        map.forEach {
            if (!map.containsKey(list[0])) {
                list[0] = it.value
                list = list.sortedArray()
            } else if (it.key >= map.getValue(list[0])) {
                list[0] = it.value
                list = list.sortedArray()
            }
        }

        list.forEach {
            println("--> $it")
        }
    }

    fun pairInArray(list: IntArray, sum: Int): Boolean {
        for (i in 0..list.size - 2) {
            println("----> ${list.filter { it != list[i] }}")
            if (list.filter { it != list[i] }.contains(sum - list[i])) {
                println("${list[i]} | $i | ${sum - list[i]}")
                return true
            }
        }
        return false
    }


    fun findMin(nums: IntArray): Int {
        val a = nums[0] / 2
        println()
        return nums.minOrNull()!!
    }


    fun findingUsersActiveMinutes(logs: Array<IntArray>, k: Int): IntArray {
        val actualLogs = ArrayList<IntArray>()
        val finalArray = ArrayList<Int>()
        for (i in 0 until k) {
            finalArray.add(0)
        }
        val realFinalArray = ArrayList<Int>()
        for (i in 0 until k) {
            realFinalArray.add(0)
        }
        val new = ArrayList<IntArray>()
        val toRemove = ArrayList<IntArray>()
        for (i in logs.indices) {
            if (new.contains(logs[i])){
                println("------->" + logs[i].toMutableList())
                toRemove.add(logs[i])
            }
            new.add(logs[i])
        }
        actualLogs.addAll(new.filter { !toRemove.contains(it) })

        actualLogs.forEachIndexed { index, ints ->
            println("---> ${ints.toMutableList()}")
            val person = ints[0]
            val time = ints[1]
            finalArray[person] = time
        }
        finalArray.forEachIndexed { index, i ->
            realFinalArray[index] = finalArray.count { it == i }
        }
        return realFinalArray.toIntArray()
    }

}

