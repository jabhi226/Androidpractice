package com.example.mym_posdemomvvm.tests

import org.json.JSONObject

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

    fun rotate(matrix: Array<IntArray>): Unit {
        val n = matrix.size - 1
        val finalList = Array(n + 1) { IntArray(n + 1) }
        for (i in 0 .. n){
            val array = IntArray(n + 1)
            for (j in 0 .. n){
                println("$i | ${n - j} | ${matrix[i][n - j]}")
                array[j] = matrix[n - j][i]
            }
            finalList[i] = array
        }
        finalList.copyInto(matrix)
        finalList.forEach {
            it.forEach { a -> print("$a | ") }
        }
    }

    fun garbageCollection(garbage: Array<String>, travel: IntArray): Int {
        var metalCount = 0
        var paperCount = 0
        var glassCount = 0
        garbage.forEachIndexed { index, it ->
            if (it.contains("M")){
                metalCount += it.count { it == 'M' }
            }
            if (it.contains("P")){
                paperCount += it.count { it == 'P' }
            }
            if (it.contains("G")){
                glassCount += it.count { it == 'G' }
            }
            if (index > 0){
                metalCount += travel[index-1]
                paperCount += travel[index-1]
                glassCount += travel[index-1]
            }
            println("$index | $it | $metalCount | $paperCount | $glassCount | ${metalCount + paperCount + glassCount}")
        }
        var mCounter = false
        var gCounter = false
        var pCounter = false
        var index = 0
        for (it in garbage.reversed()){
            if (mCounter && gCounter && pCounter) break
            if (index < travel.size){
                if (!it.contains("M") && !mCounter){
                    metalCount -= travel[travel.size-1-index]
                } else {
                    mCounter = true
                }
                if (!it.contains("P") && !pCounter){
                    paperCount -= travel[travel.size-1-index]
                } else {
                    pCounter = true
                }
                if (!it.contains("G") && !gCounter){
                    glassCount -= travel[travel.size-1-index]
                } else {
                    gCounter = true
                }
            }
            index++
            println("$index | $it | $metalCount | $paperCount | $glassCount | ${metalCount + paperCount + glassCount}")
        }
        garbage.reversed().forEachIndexed { index, it ->
        }

        return metalCount + paperCount + glassCount
    }

    fun t1(){
        println(Math.log(123.0))
    }

    fun findDuplicate(nums: IntArray): Int {
        val a =  nums.clone().sortedArray()
        for (i in a.indices) {
            if (i == 0) continue
            if (a[i] == a[i - 1])
                return a[i]
        }
        return -1
    }

    fun getCommon(nums1: IntArray, nums2: IntArray): Int {

        return -1
    }

    fun binarySearch(test: IntArray, target: Int): Int {
        if (target == test[test.size / 2]) {
            return test.size / 2
        }
        if (target < test[test.size / 2]){
            binarySearch(test.copyOfRange(0, test.size / 2), target)
        } else {
            binarySearch(test.copyOfRange((test.size / 2) + 1, test.size - 1), target)
        }
        return -1
    }


    fun minPartitions(n: String): Int {
//        return n.max().toString().toInt()
        return -1
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    private var leafTotal = 0

    fun deepestLeavesSum(root: TreeNode?): Int {
        getLeafNode(root)
        return leafTotal
    }

    private fun getLeafNode(root: TreeNode?) {
        if (root == null) return
        if (root.left == null && root.right == null){
            leafTotal += root.`val`
        }
        if (root.left != null){
            getLeafNode(root)
        }
        if (root.right != null){
            getLeafNode(root)
        }
    }

    fun deepestLeavesSum1(root: TreeNode?){
        var totalSum = 0
        var currentRoot = root
        if (root == null) return
        while (root.left == null && root.right == null){
            totalSum += root.`val`
        }
    }

    fun minOperations(n: Int): Int {
        var count = 0
        for (i in 0 until (n / 2)) {
            val last = (2 * (n - 1 - i)) + 1
            val first = (2 * i) + 1
            count += (last - first) / 2

        }
        return count
    }

    fun permute(nums: IntArray): List<List<Int>> {
        var total = 0
        val finalList = arrayListOf<List<Int>>()
        if (nums.size == 1) {
            finalList.add(listOf(nums[0]))
        }
        for (j in nums.indices) {
            for (i in nums.indices) {
                if (i == nums.size - 1){
                    break
                }
                val temp = nums[i + 1]
                nums[i + 1] = nums[i]
                nums[i] = temp
                total++
                finalList.add(nums.toList())
                nums.forEach {
                    print(" $it")
                }
                println(". $total")
            }
        }
        return emptyList()
    }

    fun uniquePaths(m: Int, n: Int): Int {
        for (i in 0 until 100){

        }
        return -1
    }

    fun removeDuplicates(nums: IntArray): Int {
        var finalCount = 0
        var endCount = 0
        val finalArray = arrayListOf<Int>()
        var currentNumberCount = 0
        var currentNumber = nums[0]
        nums.forEach {
            if (currentNumber != it) {
                currentNumber = it
                currentNumberCount = 0
            }
            currentNumberCount++
            if (currentNumberCount < 3) {
                finalCount++
                finalArray.add(it)
            } else {
                finalArray.add(finalArray.size-1-endCount,it)
                endCount++
            }
        }
        return finalCount
    }

    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }
    fun mergeNodes(head: ListNode?): ListNode? {
        var currentHead = head
        val list = arrayListOf<Int>()
        var total = 0
        while (currentHead?.`val` != null){
            if (currentHead.`val` != 0){
                total += currentHead.`val`
            } else {
                list.add(total)
                total = 0
            }
            currentHead = currentHead.next
        }
        var a = getFinalNode(null, list)
        while (a?.`val` != null){
            println("--> " + a.`val` )
            a = a.next
        }
        return null
    }

    private fun getFinalNode(nodeList: ListNode?, num: List<Int>): ListNode?{
        if (num.isEmpty()){
            return nodeList
        }
        if (nodeList == null){
            getFinalNode(ListNode(num[num.size - 1]), num.drop(num.size - 1))
        } else {
            val n = ListNode(num[num.size - 1])
            n.next = nodeList
            getFinalNode(n, num.drop(num.size - 1))
        }
        return nodeList
    }



    fun gameOfLife(board: Array<IntArray>): Unit {
//        val newBoard = Array(board.size){ Array(board[0].size){ 0 }.toIntArray() }
        board.forEachIndexed { i, row ->
            row.forEachIndexed { j, cell ->
                var neighbour = 0
                for (m in -1 .. 1){
                    for (n in -1 .. 1){
                        if (m == 0 && n == 0){
                            continue
                        }
                        val (x, y) = Pair((m + i), (n + j))
                        if (x < 0 || x > board.size - 1){
                            continue
                        }
                        if (y < 0 || y > board[0].size - 1){
                            continue
                        }
                        neighbour += board[x][y]
                    }
                }

                when {
                    (cell == 1 && (neighbour > 3 || neighbour < 2)) -> {
                        board[i][j] = 0
                    }
                    (cell == 1 && (neighbour == 2 || neighbour == 3)) -> {
                        board[i][j] = 1
                    }
                    (cell == 0 && neighbour == 3) -> {
                        board[i][j] = 1
                    }
                }
            }
        }
//        newBoard.copyInto(board)
        board.forEach {
            it.forEach {i ->
                print("$i |")
            }
            println()
        }
    }

    fun increaseLetters(s: String): String{
        var res = ""
        s.forEachIndexed { index, it ->
            var char = it.plus(1000 % 26)
            if (char.code > 122){
                char = Char(96 + char.code - 122)
            }
            print(char)
            res += char.toString()
        }
        return res
    }

    fun removeDuplicateLetters(s: String): String {
        var a = ""
        s.reversed().toSet().forEach {
            a += it
        }
        return a
    }

    fun letterCombinations(digits: String): List<String> {

        return listOf()
    }

    fun testLinkedIn(){
        println(::t1.invoke())
        println(3 as? String)

        listOf<Int>().forEach {
        }
    }

    //[[10,6,9,1],[7,5,11,2],[4,8,3,15]]
    fun sortTheStudents(score: Array<IntArray>, k: Int): Array<IntArray> {
        val json = JSONObject()
        json.put("discc", null)
        println(if (json.optDouble("discc").isNaN()) 0.0 else json.optDouble("discc"))
//        for (i in score.indices){
//            for (j in 0 until score.size - 1 - i) {
//                if (score[j][k] < score[j + 1][k]){
//                    val t = score[j]
//                    score[j] = score[j + 1]
//                    score[j + 1] = t
//                }
//            }
//        }
        return score
    }

    fun minPairSum(nums: IntArray): Int {
//        for (i in nums.indices){
//            for (j in 0 until nums.size - i - 1){
//                if (nums[j] > nums[j + 1]){
//                    val t = nums[j]
//                    nums[j] = nums[j + 1]
//                    nums[j + 1] = t
//                }
//            }
//        }
        val n1 = nums.sortedArray()
        println("---> ${n1[0]} | ${n1[nums.size - 1]}" )
        println(nums.joinToString(", "))
        var largest = -1
        for (i in 0 until nums.size / 2){
            val l = n1[i] + n1[nums.size - 1 - i]
            if (l > largest)
                largest = l
        }
        return largest
    }

}
// (11 - 1) / 2
// (9 - 3) / 2
// (7 - 5) / 2

