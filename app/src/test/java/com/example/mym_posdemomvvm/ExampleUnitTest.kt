package com.example.mym_posdemomvvm

import com.example.mym_posdemomvvm.utils.Pracs
import com.google.common.truth.Truth
import org.junit.Test

class ExampleUnitTest {

    @Test
    fun sortTheStudents() {
        val test = Pracs.t1()
        Truth.assertThat(test).isEqualTo(Unit)
    }
}