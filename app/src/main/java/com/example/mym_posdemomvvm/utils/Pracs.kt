package com.example.mym_posdemomvvm.utils

import android.os.Build
import androidx.annotation.RequiresApi

object Pracs {

    @RequiresApi(Build.VERSION_CODES.N)
    fun t1() {
        val list = listOf(1, 3, 5, 7, 2, 5, 10)
        println(list.sortedDescending()[1])
        println(list.distinct().map { it to list.count { a -> a == it } })
        println(list.groupBy { n -> n }.map { it.key to it.value.count() })
        val list1 =  listOf("ABC", "XYZ", "Abhi", "Test", "androidn")
        println(list1.filter { it.lowercase().contains("a") })

        val nums = listOf(1, 3, 6, 7)
        nums.map {
            it * it
        }
        println("---> ${nums.joinToString(", ")}")
    }


}