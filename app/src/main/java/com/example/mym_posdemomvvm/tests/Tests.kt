package com.example.mym_posdemomvvm.tests

import android.os.Build
import android.text.TextUtils
import android.util.Patterns
import androidx.core.text.isDigitsOnly
import org.json.JSONArray
import org.json.JSONObject
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern
import kotlin.math.roundToInt

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
        var previousChar = typed[currentIndex - 1]

        name.forEachIndexed { index, c ->
            var counter = true
            var isValid = false
            while (counter) {
                if (typed.length > currentIndex) {
                    if (c == typed[currentIndex] || (c == previousChar)) {
                        println(c)
                        previousChar = typed[currentIndex]
                        currentIndex++
                        isValid = true
                    } else {
                        counter = false
                    }
                } else if (typed.length == currentIndex) {
                    if (c == previousChar) {
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
            if (!isValid) {
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

    fun isValidWebsite(s: String): Boolean {
        print("----------> ")
        val split = s.split(":")
        if (split.size == 3) {
            println("BASE: ${split[0]}:${split[1]} | PORT: ${split[2]}")
        } else if (split.size == 2) {
            if (split[0].lowercase() == "http") {
                println("BASE: ${split[0]}:${split[1]} | PORT: 80")
            } else if (split[0].lowercase() == "https") {
                println("BASE: ${split[0]}:${split[1]} | PORT: 443")
            }
        }
        return true
    }

    fun isLong(s: String): Long {
        return s.toLong()
    }

    fun largestOdd(s: String): String {
        for (i in s.length - 1 downTo 0) {
            if (s[i].toString().toInt() % 2 == 1) {
                return s.subSequence(0, i + 1).toString()
            }
        }
        return ""
    }

    fun someCandy(candyType: IntArray): Int {

        print("[")
        for (i in -100..100) {
            print("$i, ")
        }
        print("]")
        val a = candyType.size / 2
        val b = candyType.distinct().size
        return if (b > a) {
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
            if (i % 2 == 0 && s[i].digitToInt() != firstChar) {
                count++
            } else if (i % 2 == 1 && s[i].digitToInt() == firstChar) {
                count++
            }
        }
        return count
    }


    //    [[1,2],[2,3],[3,4],[4,5],[5,6],[6,7]]
    fun checkStraightLine(coordinates: Array<IntArray>): Boolean {
        print("--------->   [")
        var previousArray: IntArray? = null
        var innerDifference = Int.MIN_VALUE
        var isInvalid = true
        coordinates.forEach {
            print('[')
            it.forEach { t ->
                print("$t,")
            }
            if (previousArray != null) {
                println("->->-> " + (it[1] - previousArray!![1]) / (it[0].toDouble() - previousArray!![0].toDouble()))

                if (previousArray!![0] - it[0] != previousArray!![1] - it[1]) {
                    return false
                    // isInvalid = false
                }
            }
            previousArray = it
            if (innerDifference == Int.MIN_VALUE) {
                innerDifference = it[1] - it[0]
            }
            if (innerDifference != (it[1] - it[0])) {
                return false
                // isInvalid = false
            }
            print("],")
        }
        println("]")
        // return isInvalid
        return true
    }

    fun compareVersion(version1: String, version2: String): Int {
        val v1 = version1.split(".")
        val v2 = version2.split(".")

        var diff = 0.0
        if (v1.size > v2.size) {
            v1.forEachIndexed { index, s ->
                diff += if (v2.size < index) {
                    Math.pow(v1[index].toDouble() * 10.0, -index.toDouble())
                } else if (v2.size > index) {
                    Math.pow(
                        (v1[index].toInt() - v2[index].toInt()) * (10).toDouble(),
                        -index.toDouble()
                    )
                } else {
                    0.0
                }
            }
        } else {
            v2.forEachIndexed { index, s ->
                diff += if (v1.size < index) {
                    Math.pow(v1[index].toDouble() * 10.0, -index.toDouble())
                } else if (v1.size > index) {
                    Math.pow(
                        (v1[index].toInt() - v2[index].toInt()) * (10).toDouble(),
                        -index.toDouble()
                    )
                } else {
                    0.0
                }
            }
        }
        return if (diff < 0) {
            -1
        } else if (diff > 0) {
            1
        } else {
            0
        }
    }

    fun abc() {
        val list = listOf<String>("A", "B", "C", "D", "E", "F")
        println("----->>>>" + list.windowed(list.size / 2, list.size / 2))
        println("----->>>>" + list.zipWithNext())
    }

    fun getDiscountFromMap(keyValuePairs: List<String>): Double {
        for (pair in keyValuePairs) {
            val values = pair.split("=")
            values.forEachIndexed { index, s ->
                println("---> $index | $s")
            }
            if (values[0].trim() == "patientDiscount") {
                val disc = values[1]
                if (!disc.contains("null")) {
                    return disc.toDouble()
                }
            }
        }
        return 0.0
    }


    fun hammingWeight(n: Int): Int {
        println("---> $n")
        val a = n.toUInt().toString(2).filter { it == '1' }.count()
        println("---> $a")
        return a
    }

    fun makeGood(s: String): String {
        var newString = s
        for (c in s.indices) {
            if (c + 1 == s.length) {
                break
            }
            val a = s[c]
            val b = s[c + 1]
            if (a.uppercase() == s[c + 1].uppercase()
                && a.isLowerCase()
                && b.isUpperCase()
            ) {
                newString =
                    newString.substring(0, c) + newString.subSequence(c + 2, newString.lastIndex)
            }
        }
        return newString
    }

    fun getDate(invoiceDate: String): String {
//        String newDate = "01/" + invoiceDate;
        val list = invoiceDate.split("/")
        val a = list[0] + "/20" + list[1]
        val parser = SimpleDateFormat("MM/yyyy", Locale.US)
        var date = Date()
        try {
            date = parser.parse(a)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        val parser1 = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
        val ab: String = parser1.format(date)
        println(ab)
        return ab
    }

    fun sortSentence(s: String): String {
        val list = s.split(" ").toMutableList()
        list.forEachIndexed { index, s1 ->
            list[index] = s1.reversed()
        }

        val result = StringBuilder()
        list.sorted().forEach {
            result.append(it.drop(1).reversed() + " ")
        }

        return result.toString().trim()
    }

    fun getRoundOff(num: Double): Double {
        val a = (15.99 * 5.0)
        val b = (15.99 * 5.0 * (12.0 / 100.0))
        println("-----> $a | $b | ${(a + b)}")
        return String.format(Locale.ENGLISH, "%.2f", (a + b)).toDouble()
    }


    fun getTotal(totalAmt: Double): Double {
        val billTotal: Double = totalAmt.roundToInt().toDouble()
        val roundOff = getRoundOff(totalAmt - billTotal)

        println("--> $billTotal")
        println("--> $roundOff")
        return billTotal
    }

    fun groupThePeople(groupSizes: IntArray): List<List<Int>> {
        val list = ArrayList<List<Int>>()
        groupSizes.distinct().forEachIndexed { index, i ->
            val current = ArrayList<Int>()
            val stack: Stack<Int> = groupSizes.toCollection(Stack())
            for (i1 in 0 until i) {
                for (i2 in 0 until stack.size) {
                    if (current.size == i) {
                        break
                    }
                    if (stack.peek() == i) {
                        current.add(groupSizes.size - stack.size)
                    }
                    stack.pop()
                }
            }
            list.add(current)
        }

        val list1 = ArrayList<ArrayList<Int>>()
        val sizeList = ArrayList<Int>()
        groupSizes.forEachIndexed { index, i ->
            if (sizeList.contains(i)) {

            } else {

            }
        }
        return list
    }

    fun minPairSum(nums: IntArray): Int {
        var sum = -1
        for (i in 0..nums.sorted().size / 2) {
            val pairSum = nums[i] + nums[nums.sorted().size - 1 - i]
            if (pairSum > sum) {
                sum = pairSum
            }
        }
        return sum
    }


    fun productExceptSelf(nums: IntArray): IntArray {
        val list = ArrayList<Int>()
        nums.forEachIndexed { index, i ->

//            list.add()
        }
        return intArrayOf()
    }

    fun calculation() {
        var netTotal = 86.36
        val tcs = 0.0
        val tcsAmo = roundOff(netTotal) * (tcs / 100)
        netTotal += tcsAmo
        println("---> ${roundOff(tcsAmo)} | ${roundOff(netTotal)}")
    }

    private fun roundOff(amtToRound: Double): Double {
        return String.format(Locale.ENGLISH, "%.2f", amtToRound).toDouble()
    }

    fun countSubstrings(s: String): Int {
        val max = s.length
        var maxCount = 0
        for (i in 0 until max) {
            for (j in i..max) {
                maxCount += checkIfPalindrome(s.substring(i, j))
            }
        }
        return maxCount
    }

    private fun checkIfPalindrome(s: String): Int {
        if (s.isEmpty())
            return 0
        for (i in 0 until s.length / 2) {
            if (s[i] != s[s.length - i - 1]) {
                return 0
            }
        }
        println("--> $s")
        return 1
    }

    fun findMinNumOfCoins(n: Int) {
        val curr = arrayOf(1, 2, 5, 10, 20, 50, 100, 200, 500, 2000)
        println(getLeastNum(curr, n, fullCurr = curr))
    }

    private fun getLeastNum(
        curr: Array<Int>,
        n: Int,
        returnArray: ArrayList<Int> = arrayListOf(),
        fullCurr: Array<Int>
    ): ArrayList<Int> {
        var sum = 0
        returnArray.forEach {
            sum += it
        }
        println("--------->$sum | $n")
        if (sum >= n){
            return returnArray
        }
        if (curr.size < 2){
            println("--> ${curr[0]}")
            returnArray.add(curr[0])
            return getLeastNum(fullCurr , n, returnArray, fullCurr)
        }
        println()
        var start = 0
        val end: Int
        if (n > curr[curr.size / 2]) {
            start = curr.size / 2
            end = curr.size - 1
        } else {
            end = curr.size / 2 - 1
        }
        val abc = curr.copyOfRange(start, end)
        abc.forEach {
            print("$it ,")
        }
        return getLeastNum(abc, n, returnArray, fullCurr)
    }

    fun medicineLevelDiscount(){
        val billLevelDisc = 33.0
        var medicineLevelDisc = 13.0
        val medMax = 00.0

        if (medicineLevelDisc <= 0.0){
            medicineLevelDisc = billLevelDisc
        }

        if (billLevelDisc > 0.0 && medicineLevelDisc > billLevelDisc){
            medicineLevelDisc = billLevelDisc
        }

        if (medMax > 0.0 && medicineLevelDisc > medMax){
            medicineLevelDisc = medMax
        }
        println("----------> $medicineLevelDisc")

    }

    fun emptyArray(){
        val a = arrayListOf<String>()
        val final = ArrayList<String>()
        final.addAll(a)
        println("---->" + final.size.toString())
    }

    //]]][[[
    //[[][]]
    fun somethingMinSwap(s: String): Int{
        if (s.isEmpty()) return 0
        val l = s.length - 1
        var swap = 0
        var isStack = false
        for (i in 0 .. l / 2) {
            val lastIndex = l - 1
            if (s[i] != s[lastIndex] && s[i] == '['){// both are right
                continue
            } else if (s[i] != s[lastIndex] && s[i] == ']') {// both are wrong
                swap++
            } else if (s[i] == s[lastIndex] && s[i] == ']') {// opening is wrong
                if (isStack){
                    swap++
                }
                isStack = !isStack
            } else if (s[i] == s[lastIndex] && s[i] == ']'){// closing is wrong
                if (isStack){
                    swap++
                }
                isStack = !isStack
            }
        }
        return swap
    }


    fun canReach(arr: IntArray, start: Int): Boolean {
        var nextIndex = start
        while (true){
            nextIndex += arr[nextIndex]
//            val currentIndex = arr[]
//            if (currentIndex == start){
//                return false
//            }
        }
    }

    fun getMedicineMrpFromName(name: String): String {
        val nameWithoutMrp = name
            .substring(0, name.lastIndexOf(" "))
            .replace(" ", "")
            .uppercase()
        return nameWithoutMrp
    }

    fun scriptGenerator(){
        val listOfTables = listOf(
            "manufacturer",
            "category",
            "generic",
            "medicine",
            "distributor",
            "doctor",
            "patient",
            "inventory",
            "inventory_medicine",
            "stock_effects",
            "orders",
            "order_item",
            "sales_payment_register",
            "payments",
            "payment_details"
        )
        listOfTables.forEachIndexed { index, s ->
            val txt = "database.execSQL(\"INSERT INTO `sync_stats_mpos` (`id`, `table_name`, `time_of_last_update`, `is_fully_updated`) VALUES (${index+1}, '$s', '2000-01-01 00:00:00', 0) \")"
            println(txt)
        }
    }

    fun getDataTypeByValue(){
        val value = "1"
        if (true) {
            println(value::class.java.simpleName)
        }
    }

    fun convertColumnToRow(){
        val h = "[{\"name\":\"Force patient\",\"key\":\"force_patient\",\"value\":\"false\"},{\"name\":\"Force doctor\",\"key\":\"force_doctor\",\"value\":\"true\"},{\"name\":\"Show discount\",\"key\":\"show_discount\",\"value\":\"true\"}]"
//        println(h)
        val reqJson = JSONArray(h)
        val finalRes = JSONObject()
//        println(reqJson.toString())
        for (i in 0 until reqJson.length()){
            val j = JSONObject(reqJson[i].toString())
            finalRes.put(j.getString("key"), j.getString("value"))
        }
//        println(finalRes.toString())

        val a = arrayListOf("force_patient_name", "force_patient_phone_number", "stop_less_stock", "force_doctor_name", "round_sales", "stop_expiry", "editable_mrp", "allow_sell_above_mrp", "allow_sell_less_than_effective_cp", "is_credit_limit", "is_margin", "retailer_id", "city", "contact", "country", "email", "phone_number", "pincode", "retailer_bar_name", "retailer_name", "retailer_version", "is_loose", "force_selling_price", "sales_return_lookback", "abated_mrp", "barcode_height", "barcode_width", "bill_foot", "bill_type", "chat_token", "end_fin_date", "expiry_alert", "expiry_default", "gst_no", "imap_host", "imap_port", "is_disc", "is_igst", "is_margin", "is_vat", "password", "pay_alert", "print_copies", "print_duplicate", "print_font", "print_foot", "print_head", "print_length", "print_width", "rep_current_day", "smtp_host", "smtp_port", "start_fin_date", "state", "vat_date", "vat_no", "bill_border", "placed_hours", "cess", "allow_return_from_sales_bill", "allow_sales_return_independent_of_customer", "cheque_length", "cheque_width", "deduction_percent", "demand_book_computation_mode", "enable_challan", "enable_live_stock", "enable_sales_register", "gst_registration_date", "is_composition_scheme", "is_hospital", "license", "lock_at_day_end", "payment_collection_counter", "request_password_on_tx", "backup_status", "confirm_before_sending_sms_sales", "disable_add_medicine", "disable_barcode_print", "enable_cash_counter", "enable_purchase_print", "enable_shifts", "isho", "last_backup_date", "on_order_save_sms", "otp_on_loyalty_claim", "prefix", "purchase_print_duplicate", "purchase_print_foot", "purchase_print_head", "purchase_print_length", "purchase_print_width", "schedule_order_sms", "set_medicine_details_post_purchase", "sms_sender_text", "voucher_foot", "voucher_head", "voucher_length", "voucher_width", "welcome_sms", "add_as_new_row_after_interval", "allow_multiple_shifts_for_user", "allow_unvalidated_sales_return", "disable_upload_of_hsncode_from_purchase_import", "enable_api_push", "enable_b2b_sales", "enable_margin_disc", "enable_print_name", "extension_live_connect_password", "extension_live_connect_username", "extension_retailio_password", "extension_retailio_username", "interval_between_scans_for_new_row", "max_expiry", "print_stock_transfer", "request_otp_on_loyalty_claim", "request_otp_on_sales", "request_password_on_delete", "request_password_on_delete_payment_bill", "request_password_on_delete_purchase_bill", "request_password_on_delete_return_bill", "request_password_on_delete_sales", "request_password_on_edit_payment_bill", "request_password_on_edit_purchase_bill", "request_password_on_edit_return_bill", "request_password_on_edit_sales", "request_password_on_edit_staged_order_bill", "request_password_on_generate_purchase_bill", "scan_window_for_barcode", "short_on_expiry_days", "show_tender_change", "sort_by_created_time", "super_admin_password", "update_meta_data", "validate_distributor_purchase_return", "enable_return_on_sales", "grp", "phase1", "phase2", "allow_backdated_sales_bill", "backup_paths", "disable_scan_for_not_in_stock_batches", "discount_loyalty_hierarchy", "discount_loyalty_program_exlusive", "donot_show_less_then_zero_stock_medicine", "enable_b2c_app", "enable_family_tree", "enable_loyalty_adjust", "enable_po_email", "enable_promise_order", "enforce_select_batch_option", "force_data_collection_for_tb", "gst_state", "gstin_on_sales", "is_discount_program_enabled", "is_loyalty_program_enabled", "notify_onboarding_on_loyalty_program", "patient_mobile_number_mandatory", "request_super_admin_for_sales_return", "require_super_admin_when_short_expiry_product", "short_expiry_range", "sort_created", "validate_card_before_applying_loyalty", "validate_card_on_redemption", "validate_card_to_earn_point", "enable_advance_payment", "enable_gross_margin", "prescription_paths", "allow_invoice_wise_payment", "default_offset", "enable_home_delivery", "enable_hsn_code", "enable_partner_incentive", "enable_sales_incentive", "enable_sms_notification", "installation_date", "interval_between_backup", "last_backup_attempt_date", "last_stock_refresh_time", "max_expiry_allowed_for_sales_return", "max_purchase_qty", "max_sales_qty", "max_tab_allowed", "search_by_salt", "sell_in_loose_only", "set_create_batch_on_purchase_as_default", "set_create_batch_on_sales_as_default", "tcs_per", "delivery_by_on_sales", "show_schema_config", "is_update_downloaded", "close_tab_after_save", "default_days_for_report_data", "disable_same_purchase_invoice", "enable_autobackup", "enable_bill_value_based_loyalty_point", "enable_discount_on_sales_register", "enable_entity", "enable_migration", "enable_sarathi", "enable_sku_barcodes", "minimum_barcode_size", "show_multi_batch_for_same_barcode", "retailer_type", "net_margin", "add_entity", "party_code", "catalog_version", "account_number", "bank_name", "ifsc", "play_sound_on_all_errors", "start_sales_with_patient", "upi", "whatsapp_number",)

        a.forEach {
//            print("pg_typeof($it) as $it, ")
//            print("case when pg_typeof($it) = 'boolean'::regtype then 'java.lang.Boolean' when pg_typeof($it) = 'character varying'::regtype then 'java.lang.String' when pg_typeof($it) = 'bigint'::regtype then 'java.lang.Long' when pg_typeof($it) = 'integer'::regtype then 'java.lang.Long' when pg_typeof($it) = 'double precision'::regtype then 'java.lang.Double' when pg_typeof($it) = 'timestamp without time zone'::regtype then 'java.sql.Timestamp' when pg_typeof($it) = 'real'::regtype then 'java.lang.Long' else 'java.lang.String' end as $it, ")
        }

        val b = arrayListOf<String>("retailer_id", "created_time", "created_by", "deleted", "remarks", "updated_time", "updated_by", "abated_mrp", "barcode_height", "barcode_width", "bill_foot", "bill_type", "chat_token", "city", "contact", "country", "email", "end_fin_date", "expiry_alert", "expiry_default", "gst_no", "imap_host", "imap_port", "is_credit_limit", "is_disc", "is_igst", "is_loose", "is_margin", "is_vat", "password", "pay_alert", "phone_number", "pincode", "print_copies", "print_duplicate", "print_font", "print_foot", "print_head", "print_length", "print_width", "rep_current_day", "retailer_bar_name", "retailer_name", "retailer_version", "smtp_host", "smtp_port", "start_fin_date", "state", "stop_less_stock", "vat_date", "vat_no", "bill_border", "placed_hours", "cess", "allow_return_from_sales_bill", "allow_sales_return_independent_of_customer", "cheque_length", "cheque_width", "deduction_percent", "demand_book_computation_mode", "editable_mrp", "enable_challan", "enable_live_stock", "enable_sales_register", "gst_registration_date", "is_composition_scheme", "is_hospital", "license", "lock_at_day_end", "payment_collection_counter", "request_password_on_tx", "round_sales", "sales_return_lookback", "stop_expiry", "backup_status", "confirm_before_sending_sms_sales", "disable_add_medicine", "disable_barcode_print", "enable_cash_counter", "enable_purchase_print", "enable_shifts", "isho", "last_backup_date", "on_order_save_sms", "otp_on_loyalty_claim", "prefix", "purchase_print_duplicate", "purchase_print_foot", "purchase_print_head", "purchase_print_length", "purchase_print_width", "schedule_order_sms", "set_medicine_details_post_purchase", "sms_sender_text", "voucher_foot", "voucher_head", "voucher_length", "voucher_width", "welcome_sms", "add_as_new_row_after_interval", "allow_multiple_shifts_for_user", "allow_unvalidated_sales_return", "disable_upload_of_hsncode_from_purchase_import", "enable_api_push", "enable_b2b_sales", "enable_margin_disc", "enable_print_name", "extension_live_connect_password", "extension_live_connect_username", "extension_retailio_password", "extension_retailio_username", "force_patient_name", "force_patient_phone_number", "force_selling_price", "interval_between_scans_for_new_row", "max_expiry", "print_stock_transfer", "request_otp_on_loyalty_claim", "request_otp_on_sales", "request_password_on_delete", "request_password_on_delete_payment_bill", "request_password_on_delete_purchase_bill", "request_password_on_delete_return_bill", "request_password_on_delete_sales", "request_password_on_edit_payment_bill", "request_password_on_edit_purchase_bill", "request_password_on_edit_return_bill", "request_password_on_edit_sales", "request_password_on_edit_staged_order_bill", "request_password_on_generate_purchase_bill", "scan_window_for_barcode", "short_on_expiry_days", "show_tender_change", "sort_by_created_time", "super_admin_password", "update_meta_data", "validate_distributor_purchase_return", "enable_return_on_sales", "grp", "phase1", "phase2", "allow_backdated_sales_bill", "backup_paths", "disable_scan_for_not_in_stock_batches", "discount_loyalty_hierarchy", "discount_loyalty_program_exlusive", "donot_show_less_then_zero_stock_medicine", "enable_b2c_app", "enable_family_tree", "enable_loyalty_adjust", "enable_po_email", "enable_promise_order", "enforce_select_batch_option", "force_data_collection_for_tb", "gst_state", "gstin_on_sales", "is_discount_program_enabled", "is_loyalty_program_enabled", "notify_onboarding_on_loyalty_program", "patient_mobile_number_mandatory", "request_super_admin_for_sales_return", "require_super_admin_when_short_expiry_product", "short_expiry_range", "sort_created", "validate_card_before_applying_loyalty", "validate_card_on_redemption", "validate_card_to_earn_point", "enable_advance_payment", "enable_gross_margin", "force_doctor_name", "prescription_paths", "allow_invoice_wise_payment", "allow_sell_above_mrp", "allow_sell_less_than_effective_cp", "default_offset", "enable_home_delivery", "enable_hsn_code", "enable_partner_incentive", "enable_sales_incentive", "enable_sms_notification", "enable_tcs", "installation_date", "interval_between_backup", "last_backup_attempt_date", "last_stock_refresh_time", "max_expiry_allowed_for_sales_return", "max_purchase_qty", "max_sales_qty", "max_tab_allowed", "search_by_salt", "sell_in_loose_only", "set_create_batch_on_purchase_as_default", "set_create_batch_on_sales_as_default", "tcs_per", "delivery_by_on_sales", "show_schema_config", "is_update_downloaded", "close_tab_after_save", "default_days_for_report_data", "disable_same_purchase_invoice", "enable_autobackup", "enable_bill_value_based_loyalty_point", "enable_discount_on_sales_register", "enable_entity", "enable_migration", "enable_sarathi", "enable_sku_barcodes", "minimum_barcode_size", "show_multi_batch_for_same_barcode", "retailer_type", "net_margin", "add_entity", "party_code", "catalog_version", "account_number", "bank_name", "ifsc", "play_sound_on_all_errors", "start_sales_with_patient", "upi", "whatsapp_number", "blur_margin_on_sales", "branch_name", "compulsory_print_h1", "force_remarks_on_adjustment", "retailer_short_name", "enable_whatsapp_share", "enable_email_share", "enable_reorder", "show_reorder", "allow_adjustment_stock_audit", "notify_stage_orders", "prescription_compulsory", "strict_medicine_search", "allow_alphabets_in_barcode", "avoid_db_on_pr", "enable_sales_barcode", "show_medicine_master_on_sales_bill")
        b.sorted().forEach {
//            print("$it, ")
            print("case when pg_typeof($it) = 'boolean'::regtype then 'java.lang.Boolean' when pg_typeof($it) = 'character varying'::regtype then 'java.lang.String' when pg_typeof($it) = 'bigint'::regtype then 'java.lang.Long' when pg_typeof($it) = 'integer'::regtype then 'java.lang.Long' when pg_typeof($it) = 'double precision'::regtype then 'java.lang.Double' when pg_typeof($it) = 'timestamp without time zone'::regtype then 'java.sql.Timestamp' when pg_typeof($it) = 'real'::regtype then 'java.lang.Long' else 'java.lang.String' end as $it, ")
        }

        b.forEach {
        }
    }


}






