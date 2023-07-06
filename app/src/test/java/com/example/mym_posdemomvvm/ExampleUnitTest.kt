package com.example.mym_posdemomvvm

import com.example.mym_posdemomvvm.tests.Tests
import com.example.mym_posdemomvvm.utils.Pracs
import com.google.common.truth.Truth
import org.junit.Test

import org.junit.Assert.*

class ExampleUnitTest {

    @Test
    fun sortTheStudents() {
        val test = Pracs.t1()
        Truth.assertThat(test).isEqualTo(2)
    }
}