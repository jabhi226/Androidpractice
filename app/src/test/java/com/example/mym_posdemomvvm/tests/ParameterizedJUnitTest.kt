package com.example.mym_posdemomvvm.tests

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters

@RunWith(value = Parameterized::class)
class ParameterizedJUnitTest(private val input: String, private val expectedValue: Int) {

    @Test
    fun test(){
        val t = Tests.lengthOfLastWord(input)
        assertThat(t).isEqualTo(expectedValue)
    }

    companion object {

        @JvmStatic
        @Parameters
        fun getData(): List<Array<Any>> {
            return listOf(
                arrayOf("sdfj lds", 3),
                arrayOf("sdfj lds", 3),
                arrayOf("sdfj lds", 3),
                arrayOf("sdfj lds", 2)
            )
        }
    }
}